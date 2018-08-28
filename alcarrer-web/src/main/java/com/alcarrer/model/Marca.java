package com.alcarrer.model;

import java.io.Serializable;

import com.alcarrer.enums.StatusEnum;

public class Marca implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	private StatusEnum status;

	public Marca() {

	}

	public Marca(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public Marca(String nome, String descricao) {
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
