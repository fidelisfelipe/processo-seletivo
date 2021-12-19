package br.com.analise.migracao.solucao.proxy;

import org.apache.commons.lang3.math.NumberUtils;

import br.com.analise.migracao.solucao.bus.BusPedido;
import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.exceptions.NextItemNegocioException;
import br.com.analise.migracao.solucao.output.EntidadeImportacaoOut;
import br.com.analise.migracao.solucao.request.RequestContext;

public class ImportacaoProxy {

	public void consultarHospitalDestino(RequestContext rc, JobProxy proxy, PedidoDto pedidoDto, BusPedido pedidoBanco)
			throws NextItemNegocioException {
		if (NumberUtils.createNumber(pedidoDto.getHospitalDestino()) != null) {
			//se a entidade requerente nao existe no destino passa para o proximo item
			EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, pedidoDto.getBoolPrestador(), rc);//Warning: para inclusao, este campo pode chegar nulo
			pedidoBanco.setHospitalDestino(outputEntidade.getEntidade());//hospitalDestino ???
		}
	}
	
	public void consultarEntidadeRequerente(RequestContext rc, JobProxy proxy, PedidoDto pedidoDto,
			BusPedido pedidoBanco) throws NextItemNegocioException {
		if (NumberUtils.createNumber(pedidoDto.getEntidadeRequerente()) != null) {
			//se a entidade requerente nao existe no destino passa para o proximo item
			EntidadeImportacaoOut outputEntidade = consultarEntidade(proxy, pedidoDto, Boolean.FALSE, rc);
			pedidoBanco.setEntidadeRequerente(outputEntidade.getEntidade());//entidadeRequerente ???
		}
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
