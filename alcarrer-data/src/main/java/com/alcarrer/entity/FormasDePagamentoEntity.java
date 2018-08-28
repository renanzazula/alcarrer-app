package com.alcarrer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "formasDePagamento")
public class FormasDePagamentoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "porcentagemDesconto")
	private int porcentagemDesconto;

	public FormasDePagamentoEntity() {
		super();
	}

	public FormasDePagamentoEntity(String nome, String descricao, int porcentagemDesconto) {
		this.nome = nome;
		this.descricao = descricao;
		this.porcentagemDesconto = porcentagemDesconto;
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

	public int getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(int porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
