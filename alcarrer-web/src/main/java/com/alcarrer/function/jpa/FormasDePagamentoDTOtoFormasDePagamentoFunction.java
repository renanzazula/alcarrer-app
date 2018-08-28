package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.FormasDePagamentoEntity;
import com.alcarrer.model.FormasDePagamento;

public class FormasDePagamentoDTOtoFormasDePagamentoFunction implements Function<FormasDePagamentoEntity, FormasDePagamento> {

	@Override
	public FormasDePagamento apply(FormasDePagamentoEntity input) {
		FormasDePagamento output = new FormasDePagamento();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		output.setPorcentagemDesconto(input.getPorcentagemDesconto());
		return output;
	}

}
