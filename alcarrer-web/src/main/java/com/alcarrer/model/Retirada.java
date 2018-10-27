package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class Retirada implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String descricao;
	private Date dataHora;
	private Double valor;
	private Caixa caixa;
 
}
