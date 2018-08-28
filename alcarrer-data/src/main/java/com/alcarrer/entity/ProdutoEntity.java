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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alcarrer.enums.StatusEnum;

@Entity(name = "produto")
public class ProdutoEntity implements Serializable {

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

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval= true)
	@JoinColumn(name = "produto_codigo")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;
	
	public ProdutoEntity() {

	}

	public ProdutoEntity(String nome, StatusEnum status, String descricao, Double preco, Double precoVenda,
			Double precoCusto, Double precoOferta, Double desconto, Double peso, Integer porcentagem,
			Integer porcentagemDesconto, Date dataHoraCadastro, MarcaEntity marca, FornecedorEntity fornecedor,
			CategoriaEntity categoria, SubCategoriaEntity subCategoria,
			Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida) {
		super();

		this.nome = nome;
		this.status = status;
		this.descricao = descricao;
		this.preco = preco;
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
		this.precoOferta = precoOferta;
		this.desconto = desconto;
		this.peso = peso;
		this.porcentagem = porcentagem;
		this.porcentagemDesconto = porcentagemDesconto;
		this.dataHoraCadastro = dataHoraCadastro;
		this.marca = marca;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
		this.subCategoria = subCategoria;
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getPrecoOferta() {
		return precoOferta;
	}

	public void setPrecoOferta(Double precoOferta) {
		this.precoOferta = precoOferta;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Integer getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Integer porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public SubCategoriaEntity getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoriaEntity subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Set<ProdutoHasItensTipoMedidaEntity> getProdutoHasItensTipoMedida() {
		return produtoHasItensTipoMedida;
	}

	public void setProdutoHasItensTipoMedida(Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida) {
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}

	public MedidaEntity getMedida() {
		return medida;
	}

	public void setMedida(MedidaEntity medida) {
		this.medida = medida;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}	

}
