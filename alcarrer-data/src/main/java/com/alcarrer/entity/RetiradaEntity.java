package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "retirada")
public class RetiradaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dataHora")
	private Date dataHora;

	@Column(name = "valor")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;

	public RetiradaEntity() {

	}

	public RetiradaEntity(String descricao, Date dataHora, Double valor, CaixaEntity caixa) {
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

	public CaixaEntity getCaixa() {
		return caixa;
	}

	public void setCaixa(CaixaEntity caixa) {
		this.caixa = caixa;
	}

}
