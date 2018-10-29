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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "vendaHasItemProduto")
@Entity(name = "venda")
public @Data class VendaEntity implements Serializable {

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

	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "caixa_codigo")
	private CaixaEntity caixa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_codigo")
	private ClienteEntity cliente;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "formasDePagamento_codigo")
	private FormasDePagamentoEntity formaDePagamento;

	@NotNull
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private Set<VendaHasItemProdutoEntity> vendaHasItemProduto = new HashSet<VendaHasItemProdutoEntity>();

}
