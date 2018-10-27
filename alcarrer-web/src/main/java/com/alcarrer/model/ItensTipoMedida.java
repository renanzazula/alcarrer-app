package com.alcarrer.model;

import java.io.Serializable;

import lombok.Data;

public @Data class ItensTipoMedida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String valor;
	private Medida medida;
	private Marca marca;
	private Categoria categoria;
	private SubCategoria subCategoria;

}
