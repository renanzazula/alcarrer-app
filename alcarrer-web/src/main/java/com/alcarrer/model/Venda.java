package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alcarrer.util.Util;

public class Venda implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private Date dataHora;

	private String dataHoraFormat;
	private String horaFormat;
	
	private Double valorTotal;
	private String status;
	private Caixa caixa;
	private Cliente cliente;
	private FormasDePagamento formaDePagamento;
	private List<FormasDePagamento> formasDePagamento;
	private List<Produto> produtos;

	private long quantidade;
	private Double subTotal;
	private Double valorPendente;
	private Double valorPago;
	private Double desconto;
	private Double totalApagar;
	private Double troco;
	private Double pagamento;
	private List<VendaHasItemProduto> vendaHasItemProduto;

	public Venda() {
		super();
	}

	public Venda(Integer codigo) {
		super();
		this.codigo = codigo;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<FormasDePagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<FormasDePagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public FormasDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormasDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getValorPendente() {
		return valorPendente;
	}

	public void setValorPendente(Double valorPendente) {
		this.valorPendente = valorPendente;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotalApagar() {
		return totalApagar;
	}

	public void setTotalApagar(Double totalApagar) {
		this.totalApagar = totalApagar;
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Double getPagamento() {
		return pagamento;
	}

	public void setPagamento(Double pagamento) {
		this.pagamento = pagamento;
	}

	public List<VendaHasItemProduto> getVendaHasItemProduto() {
		return vendaHasItemProduto;
	}

	public void setVendaHasItemProduto(List<VendaHasItemProduto> vendaHasItemProduto) {
		this.vendaHasItemProduto = vendaHasItemProduto;
	}

	public String getDataHoraFormat() {
		return Util.formateDate(dataHora, "dd/MM/yyyy");
	}

	public String getHoraFormat() {
		return Util.formateDate(dataHora, "HH:MM:ss");
	}

}
