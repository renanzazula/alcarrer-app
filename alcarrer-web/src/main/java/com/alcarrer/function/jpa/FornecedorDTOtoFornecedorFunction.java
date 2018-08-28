package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.FornecedorEntity;
import com.alcarrer.model.Fornecedor;

public class FornecedorDTOtoFornecedorFunction implements Function<FornecedorEntity, Fornecedor> {

	@Override
	public Fornecedor apply(FornecedorEntity input) {
		Fornecedor output = new Fornecedor();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		return output;
	}

}
