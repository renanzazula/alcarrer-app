package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "venda")
public class VendaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@CreationTimestamp
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
  	private Date data;

	@CreationTimestamp
	@Column(name = "hora")
	@Temporal(TemporalType.TIME)
	private Date hora;
	
	@Column(name = "valorTotal")
	private Double valorTotal;

	@Column(name = "status")
	private String status;

	@Column(name = "subTotal")
	private Double subTotal;

	@Column(name = "valorPendente")
	private Double valorPendente;

	@Column(name = "valorPago")
	private Double valorPago;

	@Column(name = "desconto")
	private Double desconto;

	@Column(name = "totalApagar")
	private Double totalApagar;

	@Column(name = "troco")
	private Double troco;

	@Column(name = "pagamento")
	private Double pagamento;

	@Column(name = "quantidade")
	private long quantidade;

	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;

	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "formasDePagamento_codigo")
	private FormasDePagamentoEntity formaDePagamento;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private Set<VendaHasItemProdutoEntity> vendaHasItemProduto = new HashSet<VendaHasItemProdutoEntity>();

	public VendaEntity() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public CaixaEntity getCaixa() {
		return caixa;
	}

	public void setCaixa(CaixaEntity caixa) {
		this.caixa = caixa;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FormasDePagamentoEntity getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormasDePagamentoEntity formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
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

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public Set<VendaHasItemProdutoEntity> getVendaHasItemProduto() {
		return vendaHasItemProduto;
	}

	public void setVendaHasItemProduto(Set<VendaHasItemProdutoEntity> vendaHasItemProduto) {
		this.vendaHasItemProduto = vendaHasItemProduto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	
}
