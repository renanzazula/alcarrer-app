package com.alcarrer.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = {"dominios", "vendaHasItemProduto"})
@Entity(name = "produto_has_itens_tipo_medida")
public @Data class ProdutoHasItensTipoMedidaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "quantidade")
	private Integer quantidade;

	@Column(name = "valor_unitario")
	private Double valorUnitario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itens_tipo_medida_codigo", updatable = false)
	private ItensTipoMedidaEntity itensTipoMedida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_codigo")
	private ProdutoEntity produto;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "produto_has_itens_tipo_medida_has_dominio", joinColumns = {
			@JoinColumn(name = "produto_has_itens_tipo_medida_codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "dominio_codigo") })
	private Set<DominioEntity> dominios;

	@OneToMany(mappedBy = "produtoHasItensTipoMedida", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<VendaHasItemProdutoEntity> vendaHasItemProduto = new HashSet<VendaHasItemProdutoEntity>();

	 

}
