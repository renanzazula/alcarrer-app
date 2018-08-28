package com.alcarrer.model;

import java.io.Serializable;

public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;

	public Fornecedor() {

	}

	public Fornecedor(String nome, String descricao) {
		super();
 		this.nome = nome;
		this.descricao = descricao;
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
	
}
