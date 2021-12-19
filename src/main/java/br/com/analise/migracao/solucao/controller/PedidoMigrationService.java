package br.com.analise.migracao.solucao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.analise.migracao.solucao.bus.BusEstado;
import br.com.analise.migracao.solucao.bus.BusPedido;
import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.dto.UtenteDto;
import br.com.analise.migracao.solucao.enums.TipoLastDate;
import br.com.analise.migracao.solucao.exceptions.NextItemNegocioException;
import br.com.analise.migracao.solucao.input.ConfigureIntegracaoIn;
import br.com.analise.migracao.solucao.input.ConsultarPedidoIn;
import br.com.analise.migracao.solucao.input.PedidosImportacaoIn;
import br.com.analise.migracao.solucao.input.UtentesImportacaoIn;
import br.com.analise.migracao.solucao.output.ConsultarPedidoOut;
import br.com.analise.migracao.solucao.output.EntidadeImportacaoOut;
import br.com.analise.migracao.solucao.output.PedidosImportacaoOut;
import br.com.analise.migracao.solucao.output.UtentesImportacaoOut;
import br.com.analise.migracao.solucao.proxy.JobProxy;
import br.com.analise.migracao.solucao.proxy.PedidosProxy;
import br.com.analise.migracao.solucao.request.ConfiguracaoIntegracao;
import br.com.analise.migracao.solucao.request.RequestContext;
/**
 * 	PT : Analise de Migracao de Dados
 * 
 * 	- Uma descricao, com base no codigo, de como e efetuado o processo de migracao;<br>
 * 
 * 		Realiza a convercao dos dados da requisicao para consulta/persistencia	<br>
 * 		Verifica se os dominios existem no destino para criacao ou atualizacao	<br>
 * 			Qnd nao ha usuario de um pedido, passa para o proximo item			<br>
 * 
 * 		Realiza a persistencia dos dados<br>
 * 
	- Avaliacao da clareza do codigo;<br>
		Sem Encapsulamento<br>
		Verboso<br>
		Acoplado<br>
		Poderia estar mais simples<br>
		PortuguêsInglês fica estranho
	
	- Identificacao os possiveis erros que o metodo apresenta e como podem ser corrigidos<br>
	
		Se (hospitalDestino || entidadeRequerente) nao existe, passara para o proximo pedido - <b>Warning: Nao gera rastro de itens nao migrados</b><br>
		Memoria<br>
		Caro a nivel de recursos Buffer <- BD<br>
		Monolitico<br>
		Não é feito em Batch - poderia ser
		
		Solução<br>
		
		    1º Encapsulamento - emcapsulando campos<br>
		    2º Coesão - definição de pacotes<br>
		    3º Captura de itens não migrados para solucao de pendencia
 *
 */
public class PedidoMigrationService {

	//Migra pedido do SQLServer(A) para Oracle(B)
	private void saveOrUpdatePedidos(RequestContext rc, ConfiguracaoIntegracao configuracao) throws Exception {//Spring | Primefaces | Servlet 
		List<BusPedido> pendenciaList = new ArrayList<BusPedido>();
		
		
		JobProxy proxy = new JobProxy();
		PedidosProxy pedidoProxy = new PedidosProxy();
		ConfigureIntegracaoIn confBegin = new ConfigureIntegracaoIn();
		
		//configura a requisicao do objeto de integracao
		confBegin.setTipo(TipoLastDate.PEDIDO);
		confBegin.setProcess(1);
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
			pedidoBanco.setUtente(outputUtente.getUtente());
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
			
			try {
			
				//BusHospital ??? - campo e checado mas nao utilizado - talvez internamente na consulta
				//talvez por uma questao negocial
				if (pedidoDto.getHospitalDestino() != null && NumberUtils.isNumber(pedidoDto.getHospitalDestino())) {
					EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, pedidoDto.getBoolPrestador(), rc);//Warning: para inclusao, este campo pode chegar nulo
					pedidoBanco.setHospitalDestino(outputEntidade.getEntidade());//hospitalDestino ???
				}
	
				//BusEntidadeRequerente	??? - campo e checado mas nao utilizado - talvez internamente na consulta
				if (pedidoDto.getEntidadeRequerente() != null && NumberUtils.isNumber(pedidoDto.getEntidadeRequerente())) {
					EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, Boolean.FALSE, rc);
					pedidoBanco.setEntidadeRequerente(outputEntidade.getEntidade());//entidadeRequerente ???
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
				//cria pedido no Oracle
				pedidoProxy.salvarOuAtualizar(inPedido, inserting);
			
			}catch (NextItemNegocioException e) {
				
				System.err.println("Item não processado."+pedidoDto.toString());
				
				//add pendencia
				pendenciaList.add(pedidoBanco);
				
				//set update 
				updateDate = Boolean.FALSE;
				
				//proximo item
				break;
			}
		}
		
		//persiste pendencia
		createPendenciaList(pendenciaList);
		
		//finaliza o procedimento
		updateConfig(proxy, updateDate, TipoLastDate.PEDIDO, rc);
	}


	private void createPendenciaList(List<BusPedido> pendenciaList) {
		// TODO Auto-generated method stub
		
	}


	private void updateConfig(JobProxy proxy, boolean updateDate, TipoLastDate pedido, RequestContext rc) {
		System.out.println("update config");
		
	}

	private EntidadeImportacaoOut consultarEntidade(JobProxy proxy, PedidoDto pedidoDto, Object boolPrestador,
			RequestContext rc) throws NextItemNegocioException {
		System.out.println("consulta entidade no banco de destino");
		
		boolean isNaoLocalizado = Boolean.FALSE;
		if(isNaoLocalizado) {
			throw new NextItemNegocioException();
		}
		
		EntidadeImportacaoOut entidade = new EntidadeImportacaoOut();
		entidade.setEntidade(new Object());

		return entidade;
	}
	
}
