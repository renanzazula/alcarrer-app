package com.alcarrer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

 
@Entity(name = "fornecedor")
public @Data class FornecedorEntity implements Serializable {

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
	
}
