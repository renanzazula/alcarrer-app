package com.alcarrer.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.alcarrer.entity.MedidaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Medida;

public class MedidaToMedidaEntityFunction implements Function<MedidaEntity, Medida> {

	@Override
	public Medida apply(MedidaEntity input) {
		Medida output = new Medida();
		output.setCodigo(input.getCodigo());
		output.setNome(input.getNome());
		output.setDescricao(input.getDescricao());
		
		if (input.getItensTipoMedida() != null) {
			output.setItensTipoMedida(input.getItensTipoMedida().stream().map(JpaFunctions.itensTipoMedidaToItensTipoMedidaEntity).collect(Collectors.toList()));
		}
		return output;
	}

}
