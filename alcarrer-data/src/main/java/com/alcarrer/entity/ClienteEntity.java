package com.alcarrer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "cliente")
public @Data class ClienteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568637406067043051L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Integer codigo;	 

}
