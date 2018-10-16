package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.VendaHasItemProdutoEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.VendaHasItemProduto;

public class VendaHasItemProdutoToVendaHasItemProdutoEntityFunction
		implements Function<VendaHasItemProdutoEntity, VendaHasItemProduto> {

	@Override
	public VendaHasItemProduto apply(VendaHasItemProdutoEntity input) {
		VendaHasItemProduto output = new VendaHasItemProduto();
		output.setProdutoHasItensTipoMedida(JpaFunctions.produtoHasItensTipoMedidaTOProdutoHasItensTipoMedidaEntity.apply(input.getProdutoHasItensTipoMedida()));
		output.setValorUnitario(input.getValorUnitario());
		output.setQuantidade(input.getQuantidade());
		return output;
	}

}
