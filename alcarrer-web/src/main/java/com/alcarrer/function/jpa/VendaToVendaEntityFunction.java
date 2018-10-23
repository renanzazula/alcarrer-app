package com.alcarrer.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.alcarrer.entity.VendaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Venda;

public class VendaToVendaEntityFunction implements Function<VendaEntity, Venda> {

	@Override
	public Venda apply(VendaEntity input) {
		Venda output = new Venda();
		output.setCodigo(input.getCodigo());
		output.setData(input.getData());
		output.setHora(input.getHora());
		output.setValorTotal(input.getValorTotal());
		output.setStatus(input.getStatus());
		output.setQuantidade(input.getQuantidade());
		output.setSubTotal(input.getSubTotal());
		output.setValorPendente(input.getValorPendente());
		output.setValorPago(input.getValorPago());
		output.setDesconto(input.getDesconto());
		output.setTotalApagar(input.getTotalApagar());
		output.setTroco(input.getTroco());
		output.setPagamento(input.getPagamento());
		output.setValorTotal(input.getValorTotal());
		output.setFormaDePagamento(JpaFunctions.formasDePagamentoToFormasDePagamentoEntity.apply(input.getFormaDePagamento()));
		output.setCliente(JpaFunctions.clienteToClienteEntity.apply(input.getCliente()));
		output.setCaixa(JpaFunctions.caixaToCaixaEntity.apply(input.getCaixa()));
		output.setVendaHasItemProduto(input.getVendaHasItemProduto().stream().map(JpaFunctions.vendaHasItemProdutoToVendaHasItemEntity).collect(Collectors.toList()));
		return output;
	}

}
