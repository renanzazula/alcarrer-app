package com.alcarrer.model;

import java.io.Serializable;

import lombok.Data;

public @Data class FormasDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private Integer porcentagemDesconto;

}
