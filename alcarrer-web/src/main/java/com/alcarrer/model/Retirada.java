package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;

public class Retirada implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String descricao;
	private Date dataHora;
	private Double valor;
	private Caixa caixa;

	public Retirada() {

	}

	public Retirada(String descricao, Date dataHora, Double valor, Caixa caixa) {
		super();
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.valor = valor;
		this.caixa = caixa;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

}
