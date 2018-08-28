package com.alcarrer.model;

import java.io.Serializable;


public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568637406067043051L;
	private Integer codigo;

	public Cliente() {
	 
	} 
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
