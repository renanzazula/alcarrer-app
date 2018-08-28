package com.alcarrer.model;

import java.io.Serializable;

public class VendaHasProduto implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;
	private Integer codigo;
	private Venda venda;
	private Produto produto;
	private Double valorUnitario;
	private Integer quantidade;

	public VendaHasProduto() {

	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
