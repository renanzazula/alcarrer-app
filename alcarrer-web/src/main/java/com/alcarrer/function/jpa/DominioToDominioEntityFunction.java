package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.DominioEntity;
import com.alcarrer.model.Dominio;

public class DominioToDominioEntityFunction  implements Function<DominioEntity, Dominio> {

	@Override
	public Dominio apply(DominioEntity input) {
		Dominio output = new Dominio();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		return output;
	}

}
