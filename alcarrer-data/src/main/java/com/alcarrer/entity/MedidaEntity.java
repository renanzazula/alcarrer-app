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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude= "itensTipoMedida")
@Entity(name = "medida")
public @Data class MedidaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	@NotBlank
	@Column(name = "nome", length = 45)
	private String nome;

	@NotBlank
	@Column(name = "descricao", length = 45)
	private String descricao;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "medida_codigo")
	@OrderBy("codigo")
	private Set<ItensTipoMedidaEntity> itensTipoMedida;

	public MedidaEntity() {

	}

	public MedidaEntity(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public MedidaEntity(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

}
