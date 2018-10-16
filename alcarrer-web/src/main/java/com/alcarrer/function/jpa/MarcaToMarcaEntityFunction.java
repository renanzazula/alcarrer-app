package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.MarcaEntity;
import com.alcarrer.model.Marca;

public class MarcaToMarcaEntityFunction implements Function<MarcaEntity, Marca> {

	@Override
	public Marca apply(MarcaEntity input) {
		Marca output = new Marca();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		return output;
	}

}
