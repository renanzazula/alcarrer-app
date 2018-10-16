package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.SubCategoriaEntity;
import com.alcarrer.model.SubCategoria;

public class SubCategoriaToSubCategoriaEntityFunction implements Function<SubCategoriaEntity, SubCategoria> {

	@Override
	public SubCategoria apply(SubCategoriaEntity input) {
		SubCategoria output = new SubCategoria();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		return output;
	}

}
