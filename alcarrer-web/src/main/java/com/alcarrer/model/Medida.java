package com.alcarrer.model;

import java.io.Serializable;
import java.util.List;

public class Medida implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	private Integer codigo;
	private String nome;
	private String descricao;
	
	private Categoria categoria;
	private SubCategoria subCategoria;
	private Marca marca;
	
	private List<Categoria> categorias;
	private List<SubCategoria> subCategorias;
	private List<Marca> marcas;
	
	private List<ItensTipoMedida> itensTipoMedida;

	public Medida() {

	}

	public Medida(Integer codigo) {
		super();
		this.codigo = codigo;
	}
	
	public Medida(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public Medida(Integer codigo, String nome, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public List<ItensTipoMedida> getItensTipoMedida() {
		return itensTipoMedida;
	}

	public void setItensTipoMedida(List<ItensTipoMedida> itensTipoMedida) {
		this.itensTipoMedida = itensTipoMedida;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public SubCategoria getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}

	public List<SubCategoria> getSubCategorias() {
		return subCategorias;
	}

	public void setSubCategorias(List<SubCategoria> subCategorias) {
		this.subCategorias = subCategorias;
	}

	
	
}
