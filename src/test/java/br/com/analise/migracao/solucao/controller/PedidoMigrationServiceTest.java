package br.com.analise.migracao.solucao.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.analise.migracao.solucao.dto.PedidoDto;
import br.com.analise.migracao.solucao.input.PedidosImportacaoIn;
import br.com.analise.migracao.solucao.output.PedidosImportacaoOut;
import br.com.analise.migracao.solucao.proxy.ImportacaoProxy;
import br.com.analise.migracao.solucao.proxy.JobProxy;
import br.com.analise.migracao.solucao.proxy.PedidosProxy;
import br.com.analise.migracao.solucao.request.ConfiguracaoIntegracao;
import br.com.analise.migracao.solucao.request.RequestContext;

@RunWith(MockitoJUnitRunner.class)
public class PedidoMigrationServiceTest {
	private PedidoMigrationService serviceSuccess;
	@Mock
	private RequestContext rcSuccess;
	@Mock
	private ConfiguracaoIntegracao configSuccess;
	@Mock
	private JobProxy jobProxySuccess;
	@Mock
	private PedidosProxy pedidosProxySuccess;
	@Mock
	private ImportacaoProxy importacaoProxySuccess;
	@Mock
	private PedidosImportacaoIn pedidosImportacaoIn;
	@Mock
	private PedidosImportacaoOut pedidosImportacaoOut;
	
	@Before
	public void setUp(){
		serviceSuccess = spy(new PedidoMigrationService());
	}
	
	@Test
	public void test3() {
		System.out.println("PedidoMigrationServiceTest.test()");
	}
	
	@Test
	public void testSolucao() throws Exception {

		List<PedidoDto> pedidoList = new ArrayList<PedidoDto>();
		PedidoDto pedidoBom = new PedidoDto();
		pedidoList.add(pedidoBom);
		PedidoDto pedidoRuim = new PedidoDto();
		pedidoList.add(pedidoRuim);
		PedidoDto outroPedidoBom = new PedidoDto();
		pedidoList.add(outroPedidoBom);

		when(serviceSuccess.createJobProxy()).thenReturn(jobProxySuccess);
		when(serviceSuccess.createPedidosProxy()).thenReturn(pedidosProxySuccess);
		when(serviceSuccess.createImportacaoProxy()).thenReturn(importacaoProxySuccess);
		when(serviceSuccess.createPedidosImportacaoIn()).thenReturn(pedidosImportacaoIn);

		when(jobProxySuccess.consultaPedidos(any())).thenReturn(jobProxySuccess);
		when(jobProxySuccess.getLista()).thenReturn(pedidoList);
		
		when(jobProxySuccess.consultarPedidoByOracle(pedidosImportacaoIn)).thenReturn(pedidosImportacaoOut);

		serviceSuccess.saveOrUpdatePedidos(rcSuccess, configSuccess);
	}

}