package br.com.analise.migracao.solucao.parse;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.analise.migracao.solucao.bus.BusEstado;
import br.com.analise.migracao.solucao.bus.BusPedido;
import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.dto.UtenteDto;
import br.com.analise.migracao.solucao.exceptions.NextItemNegocioException;
import br.com.analise.migracao.solucao.input.ConsultarPedidoIn;
import br.com.analise.migracao.solucao.input.PedidosImportacaoIn;
import br.com.analise.migracao.solucao.input.UtentesImportacaoIn;
import br.com.analise.migracao.solucao.output.EntidadeImportacaoOut;
import br.com.analise.migracao.solucao.output.UtentesImportacaoOut;
import br.com.analise.migracao.solucao.proxy.JobProxy;
import br.com.analise.migracao.solucao.request.ConfiguracaoIntegracao;
import br.com.analise.migracao.solucao.request.RequestContext;

public class ParsePedidos {

	public static UtentesImportacaoIn montarUtentesImportacaoIn(RequestContext rc, String codUtente) {
		UtentesImportacaoIn utenteInput = new UtentesImportacaoIn();
		utenteInput.setRequestContext(rc);
		utenteInput.setUtenteDto(new UtenteDto(codUtente));
		return utenteInput;
	}

	public static ConsultarPedidoIn montarConsultarPedidoIn(RequestContext rc, BusPedido pedidoBanco) {
		ConsultarPedidoIn inPedido = new ConsultarPedidoIn();
		inPedido.setRequestContext(rc);
		inPedido.setPedido(pedidoBanco);
		return inPedido;
	}

	public static void montarBusPedido(BusPedido pedidoBanco, PedidoDto pedidoDto,
				UtentesImportacaoOut outputUtente) throws NextItemNegocioException {
			pedidoBanco.setUtente(outputUtente.getUtente());
			
			pedidoBanco.setCodTipoPedido(Long.parseLong(pedidoDto.getCodTipoPedido()));
			pedidoBanco.setCreatedBy(pedidoDto.getAutorPedido());
			pedidoBanco.setCodPds(pedidoDto.getCodPds());
			pedidoBanco.setCreationDate(pedidoDto.getCreationDate());
			pedidoBanco.setUpdateDate(new Date());
			pedidoBanco.setNumPedido(pedidoDto.getCodPedido());

			//BusEstado
			if (NumberUtils.createNumber(pedidoDto.getEstadoDoPedido()) != null) {
				BusEstado estado = new BusEstado();
				estado.setCodEstado(Long.parseLong(pedidoDto.getEstadoDoPedido()));
				pedidoBanco.setEstado(estado);
			}

	}
	
	public static void montarPedidosImportacaoIn(PedidosImportacaoIn input, ConfiguracaoIntegracao configuracao, RequestContext rc) {
		input.setConfiguracao(configuracao);
		input.setRequestContext(rc);
	}
	
}
