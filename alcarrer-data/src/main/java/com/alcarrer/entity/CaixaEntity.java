package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "caixa")
public class CaixaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataHoraAbertura")
	private Date dataHoraAbertura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataHoraFechamento")
	private Date dataHoraFechamento;

	@Column(name = "valorInicial")
	private Double valorInicial;

	@Column(name = "valorFinal")
	private Double valorFinal;

	@Column(name = "total")
	private Double total;

	@Column(name = "statusCaixa")
	private String statusCaixa;

	public CaixaEntity(Date dataHoraAbertura, Date dataHoraFechamento, Double valorInicial, Double valorFinal, Double total,
			String statusCaixa) {
		super();
		this.dataHoraAbertura = dataHoraAbertura;
		this.dataHoraFechamento = dataHoraFechamento;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.total = total;
		this.statusCaixa = statusCaixa;
	}

	public CaixaEntity() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(Date dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Date getDataHoraFechamento() {
		return dataHoraFechamento;
	}

	public void setDataHoraFechamento(Date dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getStatusCaixa() {
		return statusCaixa;
	}

	public void setStatusCaixa(String statusCaixa) {
		this.statusCaixa = statusCaixa;
	}

}
