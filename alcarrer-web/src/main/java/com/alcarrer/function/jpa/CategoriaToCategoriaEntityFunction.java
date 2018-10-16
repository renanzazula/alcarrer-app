package com.alcarrer.function.jpa;

import java.util.stream.Collectors;

import com.alcarrer.entity.CategoriaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Categoria;

public class CategoriaToCategoriaEntityFunction implements java.util.function.Function<CategoriaEntity, Categoria> {

	@Override
	public Categoria apply(CategoriaEntity input) {
		Categoria output = new Categoria();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		output.setSubCategorias(input.getSubCategoriasSet().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList()));
		return output;
	}

}
