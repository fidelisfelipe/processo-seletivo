package br.com.analise.migracao.solucao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.analise.migracao.solucao.bus.BusPedido;
import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.enums.TipoLastDate;
import br.com.analise.migracao.solucao.exceptions.NextItemNegocioException;
import br.com.analise.migracao.solucao.input.ConfigureIntegracaoIn;
import br.com.analise.migracao.solucao.input.PedidosImportacaoIn;
import br.com.analise.migracao.solucao.output.PedidosImportacaoOut;
import br.com.analise.migracao.solucao.output.UtentesImportacaoOut;
import br.com.analise.migracao.solucao.parse.ParsePedidos;
import br.com.analise.migracao.solucao.proxy.ImportacaoProxy;
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
 * 			Qnd nao ha usuario de um pedido, unidade requerente, hospital de destino, passa para o proximo item	<br>
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
		ImportacaoProxy importacaoProxy = new ImportacaoProxy();

		ConfigureIntegracaoIn confBegin = new ConfigureIntegracaoIn();

		//configura a requisicao do objeto de integracao
		confBegin.setTipo(TipoLastDate.PEDIDO);
		confBegin.setProcess(1);
		confBegin.setRequestContext(rc);

		//inicia o procedimento
		proxy.updateProcess(confBegin);

		//input para consulta
		PedidosImportacaoIn input = new PedidosImportacaoIn();
		ParsePedidos.montarPedidosImportacaoIn(input, configuracao, rc);

		//obtem os dados do SqlServer com as informacoes da requisicao
		List<PedidoDto> pedidoList = proxy.consultaPedidos(input).getLista();

		boolean updateDate = true;
		for (PedidoDto pedidoDto : pedidoList) {

			boolean inserting = false;
			input.setPedidoDto(pedidoDto);

			//BusPedido
			BusPedido pedidoBanco = null;
			try {
				//confere a existencia do Pedido na base de destino
				PedidosImportacaoOut outputPedido = proxy.consultarPedidoByOracle(input);

				//define Criar ou Atualizar um Pedido
				if (outputPedido != null)//obtem o Pedido caso exista
					pedidoBanco = outputPedido.getPedido();

				if (pedidoBanco == null) {//cria um novo Pedido caso nao exista
					inserting = true;
					pedidoBanco = new BusPedido();
				}

				//se o Usuario nao existe no destino passa para o proximo item
				UtentesImportacaoOut outputUtente = 
						proxy.consultarUtenteByOracle(ParsePedidos.montarUtentesImportacaoIn(rc, pedidoDto.getCodUtente()));

				//monta dados para persistencia
				ParsePedidos.montarBusPedido(pedidoBanco, pedidoDto, outputUtente);

				//se a EntidadeRequerente nao existe no destino passa para o proximo item
				importacaoProxy.consultarHospitalDestino(rc, proxy, pedidoDto, pedidoBanco);

				//se o HospitalDestino nao existe no destino passa para o proximo item
				importacaoProxy.consultarEntidadeRequerente(rc, proxy, pedidoDto, pedidoBanco);

				//cria/atualiza pedido no Oracle
				pedidoProxy.salvarOuAtualizar(ParsePedidos.montarConsultarPedidoIn(rc, pedidoBanco), inserting);

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

}
