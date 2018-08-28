package com.alcarrer.model;

import java.io.Serializable;
import java.util.List;

public class ProdutoHasItensTipoMedida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private List<Dominio> dominios;
	private Integer quantidade;
	private Double  valorUnitario;
	private ItensTipoMedida itensTipoMedida;
	private Produto produto;

	public ProdutoHasItensTipoMedida() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ItensTipoMedida getItensTipoMedida() {
		return itensTipoMedida;
	}

	public void setItensTipoMedida(ItensTipoMedida itensTipoMedida) {
		this.itensTipoMedida = itensTipoMedida;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Dominio> getDominios() {
		return dominios;
	}

	public void setDominios(List<Dominio> dominios) {
		this.dominios = dominios;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
