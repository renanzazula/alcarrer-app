package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "itens_tipo_medida")
public class ItensTipoMedidaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "valor")
	private String valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medida_codigo")
	private MedidaEntity medida;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marca_codigo")
	private MarcaEntity marca;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_codigo")
	private CategoriaEntity categoria;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sub_categoria_codigo")
	private SubCategoriaEntity subCategoria;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "itens_tipo_medida_codigo")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;


	public ItensTipoMedidaEntity() {

	}

	public SubCategoriaEntity getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoriaEntity subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public MedidaEntity getMedida() {
		return medida;
	}

	public void setMedida(MedidaEntity medida) {
		this.medida = medida;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}
	
	public Set<ProdutoHasItensTipoMedidaEntity> getProdutoHasItensTipoMedida() {
		return produtoHasItensTipoMedida;
	}

	public void setProdutoHasItensTipoMedida(Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida) {
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}

}
