package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alcarrer.enums.StatusEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "produto")
@EqualsAndHashCode(exclude = "produtoHasItensTipoMedida")
public @Data class ProdutoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2203862074139518315L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo", updatable = false, nullable = false)
	private Integer codigo;

	/**
	 * codigo -> sequencial (from dataBase) marca_codigo fornecedor_codigo
	 * categoria_codigo subcategoria_codigo medida_codigo flagSite -> : LFB -> loja
	 * fisica born : LOW -> loja online Wix
	 */
	@Column(name = "barCode")
	private String barCode;

	@Column(name = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco")
	private Double preco;

	@Column(name = "precoVenda")
	private Double precoVenda;

	@Column(name = "precoCusto")
	private Double precoCusto;

	@Column(name = "precoOferta")
	private Double precoOferta;

	@Column(name = "desconto")
	private Double desconto;

	@Column(name = "peso")
	private Double peso;

	@Column(name = "porcentagem")
	private Integer porcentagem;

	@Column(name = "porcentagemDesconto")
	private Integer porcentagemDesconto;

	@Lob
	@Column(name = "foto", columnDefinition = "BLOB")
	private byte[] foto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataHoraCadastro")
	private Date dataHoraCadastro;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "marca_codigo", updatable = false)
	private MarcaEntity marca;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fornecedor_codigo")
	private FornecedorEntity fornecedor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_codigo")
	private CategoriaEntity categoria;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medida_codigo")
	private MedidaEntity medida;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_categoria_codigo")
	private SubCategoriaEntity subCategoria;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "produto_codigo")
	@OrderBy("itensTipoMedida")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;

	 

}
