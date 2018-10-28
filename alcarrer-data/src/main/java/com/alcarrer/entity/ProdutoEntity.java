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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;

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

	@NotBlank
	@Column(name = "nome", length = 45)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status;

	@NotBlank
	@Column(name = "descricao", length = 45)
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

	@NotNull
	@Size(min=0, max=100)
	@Column(name = "porcentagem")
	private Integer porcentagem;

	@NotNull
	@Size(min=0, max=100)
	@Column(name = "porcentagemDesconto")
	private Integer porcentagemDesconto;

	@Lob
	@Column(name = "foto", columnDefinition = "BLOB")
	private byte[] foto;

	@NotNull
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dataHoraCadastro")
	private Date dataHoraCadastro;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "marca_codigo", updatable = false)
	private MarcaEntity marca;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fornecedor_codigo")
	private FornecedorEntity fornecedor;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_codigo")
	private CategoriaEntity categoria;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medida_codigo")
	private MedidaEntity medida;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sub_categoria_codigo")
	private SubCategoriaEntity subCategoria;

	@NotNull
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "produto_codigo")
	@OrderBy("itensTipoMedida")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;

	 

}
