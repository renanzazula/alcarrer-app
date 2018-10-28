package com.alcarrer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity(name = "formasDePagamento")
public @Data class FormasDePagamentoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@NotNull
	@Size(min =0, max = 100)
	@Column(name = "porcentagemDesconto")
	private int porcentagemDesconto;

}
