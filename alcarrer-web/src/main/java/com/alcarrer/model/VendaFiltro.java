package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;

public class VendaFiltro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6566111624858515644L;
	
	private Integer codigo;
	private Date dataHora;
	private String status;
	private Cliente cliente;
	private FormasDePagamento formaDePagamento;
	
	public VendaFiltro() {
	 
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 
	public FormasDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormasDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	 

}
