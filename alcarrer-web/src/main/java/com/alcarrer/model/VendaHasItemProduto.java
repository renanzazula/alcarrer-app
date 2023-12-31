package com.alcarrer.model;

import java.io.Serializable;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.alcarrer.util.Constants;

import lombok.Data;
 
public @Data class VendaHasItemProduto implements Serializable {

  	/**
	 * 
	 */
	private static final long serialVersionUID = -6408847193452580066L;
	
	private ProdutoHasItensTipoMedida produtoHasItensTipoMedida;
 	
	@NumberFormat(style=Style.CURRENCY, pattern=Constants.PATTERN_NUMBER_FORMAT)
	private Double valorUnitario;
	
	@NumberFormat(style=Style.NUMBER)
 	private Integer quantidade;
 	
	public ProdutoHasItensTipoMedida getProdutoHasItensTipoMedida() {
		return produtoHasItensTipoMedida;
	}
	public void setProdutoHasItensTipoMedida(ProdutoHasItensTipoMedida produtoHasItensTipoMedida) {
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

 	
 
	
}
