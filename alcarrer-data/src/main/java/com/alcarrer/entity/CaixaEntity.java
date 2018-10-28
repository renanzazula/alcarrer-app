package com.alcarrer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.NotBlank;

import com.alcarrer.enums.StatusCaixaEnum;

import lombok.Data;

@Entity(name = "caixa")
public @Data class CaixaEntity implements Serializable {

	private static final long serialVersionUID = -6612762288260227887L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;

	 
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dataAbertura")
	private Date dataAbertura;
	 
	@NotNull
	@Temporal(TemporalType.TIME)
	@Column(name = "horaAbertura")
	private Date horaAbertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataFechamento")
	private Date dataFechamento;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaFechamento")
	private Date horaFechamento;

	@NotNull
	@ColumnDefault(value = "0")
	@Column(name = "valorInicial")
	private Double valorInicial;

	@ColumnDefault(value = "0")
	@Column(name = "valorFinal")
	private Double valorFinal;

	@ColumnDefault(value = "0")
	@Column(name = "totalVendas")
	private Double totalVendas;

	@ColumnDefault(value = "0")
	@Column(name = "total")
	private Double total;

	@ColumnDefault(value = "0")
	@Column(name = "totalDesconto")
	private Double totalDesconto;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusCaixaEnum status;

 
}
