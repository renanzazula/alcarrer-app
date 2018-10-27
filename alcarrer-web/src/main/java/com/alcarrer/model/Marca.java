package com.alcarrer.model;

import java.io.Serializable;

import com.alcarrer.enums.StatusEnum;

import lombok.Data;

public @Data class Marca implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private StatusEnum status;

	 

}
