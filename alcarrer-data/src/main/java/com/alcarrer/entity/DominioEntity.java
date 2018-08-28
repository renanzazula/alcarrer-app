package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "dominio")
public class DominioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4933949406995695753L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@ManyToMany(mappedBy = "dominios")
	private Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida;

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

	public Set<ProdutoHasItensTipoMedidaEntity> getProdutoHasItensTipoMedida() {
		return produtoHasItensTipoMedida;
	}

	public void setProdutoHasItensTipoMedida(Set<ProdutoHasItensTipoMedidaEntity> produtoHasItensTipoMedida) {
		this.produtoHasItensTipoMedida = produtoHasItensTipoMedida;
	}

}
