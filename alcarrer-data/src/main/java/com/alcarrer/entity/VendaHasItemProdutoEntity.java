package com.alcarrer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "venda_has_item_produto")
public class VendaHasItemProdutoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2516119080969832005L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "venda_has_item_produto_codigo")
	private Integer codigo;

	@ManyToOne
	@JoinColumn(name = "venda_codigo")
	private VendaEntity venda;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_has_itens_tipo_medida_codigo", updatable = false)
	@OrderBy("codigo")
	private ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@Column(name = "quantidade")
	private Integer quantidade;

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public VendaEntity getVenda() {
		return venda;
	}

	public void setVenda(VendaEntity venda) {
		this.venda = venda;
	}

	public ProdutoHasItensTipoMedidaEntity getProdutoHasItensTipoMedida() {
		return produtoHasItensTipoMedida;
	}

	public void setProdutoHasItensTipoMedida(ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida) {
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}

}
