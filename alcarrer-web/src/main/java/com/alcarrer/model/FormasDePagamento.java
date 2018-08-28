package com.alcarrer.model;

import java.io.Serializable;

public class FormasDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private Integer porcentagemDesconto;

	public FormasDePagamento() {
		super();
	}

	public FormasDePagamento(String nome, String descricao, int porcentagemDesconto) {
		this.nome = nome;
		this.descricao = descricao;
		this.setPorcentagemDesconto(porcentagemDesconto);
	}
	
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	 
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Integer porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

}
