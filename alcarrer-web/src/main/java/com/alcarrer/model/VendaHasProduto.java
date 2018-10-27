package com.alcarrer.model;

import java.io.Serializable;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.alcarrer.util.Constants;

import lombok.Data;

public @Data class VendaHasProduto implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;
	private Integer codigo;
	private Venda venda;
	private Produto produto;
	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorUnitario;
	
	//FIXME: validar se necessario 
	private Integer quantidade;

}
