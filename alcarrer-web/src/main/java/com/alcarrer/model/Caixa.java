package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;

public class Caixa implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private Date dataHoraAbertura;
	private Date dataHoraFechamento;
	private Double valorInicial;
	private Double valorFinal;
	private Double total;
	private String statusCaixa;

	public Caixa(Date dataHoraAbertura, Date dataHoraFechamento, Double valorInicial, Double valorFinal, Double total,
			String statusCaixa) {
		super();
		this.dataHoraAbertura = dataHoraAbertura;
		this.dataHoraFechamento = dataHoraFechamento;
		this.valorInicial = valorInicial;
		this.valorFinal = valorFinal;
		this.total = total;
		this.statusCaixa = statusCaixa;
	}

	public Caixa() {

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
