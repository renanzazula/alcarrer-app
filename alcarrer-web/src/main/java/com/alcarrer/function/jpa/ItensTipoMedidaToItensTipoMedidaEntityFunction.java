package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.ItensTipoMedidaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.ItensTipoMedida;

public class ItensTipoMedidaToItensTipoMedidaEntityFunction implements Function<ItensTipoMedidaEntity, ItensTipoMedida> {

	@Override
	public ItensTipoMedida apply(ItensTipoMedidaEntity input) {
		ItensTipoMedida output = new ItensTipoMedida();
		output.setCodigo(input.getCodigo());
		output.setValor(input.getValor());
		
		
		if (input.getCategoria() != null) {
			output.setCategoria(JpaFunctions.categoriaToCategoriaEntity.apply(input.getCategoria()));
		}
		if (input.getSubCategoria() != null) {
			output.setSubCategoria(JpaFunctions.subCategoriaToSubCategoriaEntity.apply(input.getSubCategoria()));
		}
		if (input.getMarca() != null) {
			output.setMarca(JpaFunctions.marcaToMarcaEntity.apply(input.getMarca()));
		}
		return output;
	}

}
