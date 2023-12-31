package com.alcarrer.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public @Data class Medida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	
	private Categoria categoria;
	private SubCategoria subCategoria;
	private Marca marca;
	
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	private List<Marca> marcas;
	
	private List<ItensTipoMedida> itensTipoMedida;

	 

	
	
}
