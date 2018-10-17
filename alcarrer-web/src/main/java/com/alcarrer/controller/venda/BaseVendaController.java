package com.alcarrer.controller.venda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.alcarrer.enums.StatusVendaEnum;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.FormasDePagamento;
import com.alcarrer.model.Venda;
import com.alcarrer.model.VendaFiltro;
import com.alcarrer.service.FormasDePagamento.FormasDePagamentoService;
import com.alcarrer.service.venda.VendaService;
import com.alcarrer.util.Util;


public class BaseVendaController {

	protected static final String VIEW = "venda";
	protected static final String FINALIZAR_VENDA_VIEW = "finalizarVenda";
	protected static final String VIEW_COLSULTA = "consultarVendas";
	
	@Autowired
	protected VendaService vendaService;
	
	@Autowired
	private MessageSource message;

	@Autowired
	private FormasDePagamentoService formasDePagamentoService;


	/**
	 * Preenche combo Forma de Pagamento
	 * 
	 * @retur
	 */
	public List<FormasDePagamento> carregarFormaDePagamento() {
		return formasDePagamentoService.consultar();
	}

	/**
	 * Preenche combo Forma de Pagamento
	 * 
	 * @retur
	 */
	public StatusVendaEnum[] carregarStatusVenda() {
		return StatusVendaEnum.values();
	}

	/**
	 * 
	 * @param stepVenda
	 * @return
	 */
	protected List<BreadCrumb> breadCrumbList(String stepVenda) {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.venda");
		if (stepVenda.equalsIgnoreCase(VIEW)) {
			msg.add("menu.venda.itensVenda");
		} else {
			msg.add("menu.venda.itensVenda");
			msg.add("menu.venda.finalizar");
		}
		return Util.breadCrumbList(message, msg);
	}

	
	/**
	 * Venda
	 * @param vendaFiltro
	 * @return
	 */
	protected Venda vendaFiltroToVenda(VendaFiltro vendaFiltro) {
		Venda venda = new Venda();				
//		Cliente cliente = new Cliente();
//		cliente.setCodigo(vendaFiltro.getCliente());
//		venda.setCliente(cliente);		
		
		venda.setCodigo(vendaFiltro.getCodigo());
		venda.setDataHora(vendaFiltro.getDataHora());		
		venda.setFormaDePagamento(vendaFiltro.getFormaDePagamento());		
		venda.setStatus(vendaFiltro.getStatus());		
		return venda;
	}
}
