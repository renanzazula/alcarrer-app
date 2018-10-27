package com.alcarrer.model;

import java.io.Serializable;

import lombok.Data;

public @Data class Dominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4933949406995695753L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private boolean checked;

}
