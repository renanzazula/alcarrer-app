package com.alcarrer.entity;

import java.io.Serializable;
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
import javax.persistence.OrderBy;

import lombok.Data;

@Entity(name = "categoria")
public @Data class CategoriaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "categoria_has_sub_categoria", joinColumns = {
			@JoinColumn(name = "categoria_codigo", nullable = false, updatable = false, referencedColumnName = "codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "sub_categoria_codigo", nullable = false, updatable = false) })
	@OrderBy("codigo")
	private Set<SubCategoriaEntity> subCategoriasSet;

	public CategoriaEntity() {

	}
	
	public CategoriaEntity(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public CategoriaEntity(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	 

}
