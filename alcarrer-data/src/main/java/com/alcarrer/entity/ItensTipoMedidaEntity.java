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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "itens_tipo_medida")
@EqualsAndHashCode(exclude="produtoHasItensTipoMedida")
public @Data class ItensTipoMedidaEntity implements Serializable {

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
 
}
