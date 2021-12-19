package br.com.analise.migracao.solucao.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.analise.migracao.solucao.bus.BusEstado;
import br.com.analise.migracao.solucao.bus.BusPedido;
import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.dto.UtenteDto;
import br.com.analise.migracao.solucao.enums.TipoLastDate;
import br.com.analise.migracao.solucao.input.ConsultarPedidoIn;
import br.com.analise.migracao.solucao.output.ConsultarPedidoOut;
import br.com.analise.migracao.solucao.proxy.PedidosProxy;
import br.com.analise.migracao.solucao.request.ConfiguracaoIntegracao;
import br.com.analise.migracao.solucao.request.RequestContext;
/**
 * 	PT : Analise de Migracao de Dados
 * 
 * 	- Uma descricao, com base no codigo, de como e efetuado o processo de migracao;
 * 
 * 		Realiza a convercao dos dados da requisicao para consulta/persistencia
 * 		Verifica se os dominios existem no destino para criacao ou atualizacao
 * 			Qnd nao ha usuario de um pedido, passa para o proximo item			
 * 
 * 		Realiza a persistencia dos dados
 * 
	- Avaliacao da clareza do codigo;
		Sem Encapsulamento
		Verboso
		Acoplado
		Poderia estar mais simples
	
	- Identificacao os possiveis erros que o metodo apresenta e como podem ser corrigidos
	
		Se hospitalDestino || entidadeRequerente nao existe, passara para o proximo pedido - Warning: Nao gera rastro de itens nao migrados
		Memoria
		Caro a nivel de recursos Buffer <- BD
		Monolitico
		
	    1º Encapsulamento 
 *
 */
public class PedidoMigrationService {

	//Migra pedido do SQLServer(A) para Oracle(B)
	private void saveOrUpdatePedidos(RequestContext rc, ConfiguracaoIntegracao configuracao) throws Exception {//Spring | Primefaces | Servlet 

		JobProxy proxy = new JobProxy();
		PedidosProxy pedidoProxy = new PedidosProxy();
		ConfigureIntegracaoIn confBegin = new ConfigureIntegracaoIn();
		
		//configura a requisicao do objeto de integracao
		confBegin.setTipo(TipoLastDate.PEDIDO);
		confBegin.process = 1;
		confBegin.setRequestContext(rc);

		//inicia o procedimento - ?
		proxy.updateProcess(confBegin);

		PedidosImportacaoIn input = new PedidosImportacaoIn();
		input.setConfiguracao(configuracao);
		input.setRequestContext(rc);
		
		//obtem os dados do SqlServer com as informacoes da requisicao
		List<PedidoDto> listaPedidosDto = proxy.consultaPedidos(input).getLista();
		boolean updateDate = true;

		for (PedidoDto pedidoDto : listaPedidosDto) {

			boolean inserting = false;
			input.setPedidoDto(pedidoDto);

			//confere a existencia do Pedido na base de destino
			//o que definira Criar ou Atualizar um Pedido
			PedidosImportacaoOut outputPedido = proxy.consultarPedidoByOracle(input);

			//BusPedido
			BusPedido pedidoBanco = null;

			if (outputPedido != null)//obtem o Pedido caso exista
				pedidoBanco = outputPedido.getPedido();

			if (pedidoBanco == null) {//cria um novo Pedido caso nao exista
				inserting = true;
				pedidoBanco = new BusPedido();
			}

			UtentesImportacaoIn utenteInput = new UtentesImportacaoIn();
			utenteInput.setRequestContext(rc);
			utenteInput.setUtenteDto(new UtenteDto(pedidoDto.getCodUtente()));

			//confere a existencia do Usuario deste pedido na base de destino
			UtentesImportacaoOut outputUtente = proxy.consultarUtenteByOracle(utenteInput);

			//se o Usuario nao existe passa para o proximo pedido
			if (outputUtente == null) {
				updateDate = false;
				break;//NextItemNegocioException
			}

			//se o Usuario existe no destino, controi objeto para migracao | Convert <code>BusPedido PedidoDtoToBusPedido(pedidoDto)</code> sugestion: MapStruct
			pedidoBanco.setUtente(outputUtente.utente);
			pedidoBanco.setCodTipoPedido(Long.parseLong(pedidoDto.getCodTipoPedido()));
			pedidoBanco.setCreatedBy(pedidoDto.getAutorPedido());
			pedidoBanco.setCodPds(pedidoDto.getCodPds());
			pedidoBanco.setCreationDate(pedidoDto.getCreationDate());
			pedidoBanco.setUpdateDate(new Date());
			pedidoBanco.setNumPedido(pedidoDto.getCodPedido());

			//NumberUtils.createNumber 	- value, 
			//NumberUtils.isParsable 	- 0~9 and ponto decimal 
			//NumberUtils.isCreatable 	- hexadecimal, octal numbers, scientific notation and numbers marked with a type qualifier
			
			//BusEstado
			if (pedidoDto.getEstadoDoPedido() != null && NumberUtils.isNumber(pedidoDto.getEstadoDoPedido())) {
				BusEstado estado = new BusEstado();
				estado.setCodEstado(Long.parseLong(pedidoDto.getEstadoDoPedido()));
				pedidoBanco.setEstado(estado);
			}

			//Blocos Duplicados - talvez podem ser rezumidos na mesma logica - destinar para uma classe de regras negociais
			
			//BusHospital ??? - campo e checado mas nao utilizado - talvez internamente na consulta
			//talvez por uma questao negocial
			if (pedidoDto.getHospitalDestino() != null && NumberUtils.isNumber(pedidoDto.getHospitalDestino())) {
				EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, pedidoDto.getBoolPrestador(), rc);//Warning: para inclusao, este campo pode chegar nulo

				//se hospitalDestino nao existe passa para o proximo pedido - Warning: Nao gera rastro de itens nao migrados
				if (outputEntidade == null || outputEntidade.getEntidade() == null) {
					updateDate = false;
					break;//NextItemNegocioException
				}

				pedidoBanco.setDestino(outputEntidade.getEntidade());//hospitalDestino ???
			}

			//BusEntidadeRequerente	??? - campo e checado mas nao utilizado - talvez internamente na consulta
			if (pedidoDto.getEntidadeRequerente() != null && NumberUtils.isNumber(pedidoDto.getEntidadeRequerente())) {

				EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, false, rc);
				
				//se entidadeRequerente nao existe passa para o proximo pedido - Warning: Nao gera rastro de itens nao migrados
				if (outputEntidade == null || outputEntidade.getEntidade() == null) {
					updateDate = false;
					break;//NextItemNegocioException
				}

				pedidoBanco.setEntidade(outputEntidade.getEntidade());//entidadeRequerente ???
			}

			// O que e feito com o resultado da migracao outPedido ?
			// a referencia do objeto de retorno do insert/update e rescrita
			// o objeto nao e utilizado apos re-criacao

			//poderia ser criada uma lista de pendencia para os pedidos nao migrados para solucionar posteriormente apos algum evento quando nao houver retorno
			//poderia lancar uma NegocioException itens nao inseridos/atualizados para pendencias
			
			//poderia descartar esse objeto outPedido
			
			ConsultarPedidoOut outPedido = new ConsultarPedidoOut();//Warning: objeto nao utilizado
			ConsultarPedidoIn inPedido = new ConsultarPedidoIn();
			inPedido.setRequestContext(rc);
			inPedido.setPedido(pedidoBanco);

			//criaPedido e atualizaPedido poderiam ser um metodo so tambem
			if (inserting) {
				//cria pedido no Oracle
				outPedido = pedidoProxy.criaPedido(inPedido);
			} else {
				//atualiza pedido no Oracle
				outPedido = pedidoProxy.atualizaPedido(inPedido);
			}
		}
		
		//finaliza o procedimento
		updateConfig(proxy, updateDate, TipoLastDate.PEDIDO, rc);
	}

	private void updateConfig(JobProxy proxy, boolean updateDate, TipoLastDate pedido, RequestContext rc) {
		System.out.println("update config");
		
	}

	private EntidadeImportacaoOut consultarEntidade(JobProxy proxy, PedidoDto pedidoDto, Object boolPrestador,
			RequestContext rc) {
		System.out.println("consulta entidade no banco de destino");
		return null;
	}
	
}
