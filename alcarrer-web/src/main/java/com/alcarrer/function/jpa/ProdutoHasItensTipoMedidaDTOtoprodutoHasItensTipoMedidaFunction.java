package com.alcarrer.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.alcarrer.entity.ProdutoHasItensTipoMedidaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.ProdutoHasItensTipoMedida;

public class ProdutoHasItensTipoMedidaDTOtoprodutoHasItensTipoMedidaFunction
		implements Function<ProdutoHasItensTipoMedidaEntity, ProdutoHasItensTipoMedida> {

	@Override
	public ProdutoHasItensTipoMedida apply(ProdutoHasItensTipoMedidaEntity input) {
		ProdutoHasItensTipoMedida output = new ProdutoHasItensTipoMedida();
		output.setCodigo(input.getCodigo());
		if( input.getDominios() != null) {
			output.setDominios( input.getDominios().stream().map(JpaFunctions.dominioDTOtoDominio).collect(Collectors.toList()));		
		}
		if (input.getItensTipoMedida() != null) {
			output.setItensTipoMedida(JpaFunctions.itensTipoMedidaDTOtoItensTipoMedida.apply(input.getItensTipoMedida()));
		}
//		output.setProduto(JpaFunctions.produtoDTOtoProduto.apply(input.getProduto()));
		
		output.setQuantidade(input.getQuantidade());
		output.setValorUnitario(input.getValorUnitario());
		return output;
	}

}
