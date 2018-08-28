package com.alcarrer.model;

import java.io.Serializable;
 
public class VendaHasItemProduto implements Serializable {

  	/**
	 * 
	 */
	private static final long serialVersionUID = -6408847193452580066L;
	
	private ProdutoHasItensTipoMedida produtoHasItensTipoMedida;
 	private Double valorUnitario;
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
