package com.alcarrer.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class Error implements Serializable{
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = -7754886921722274018L;
	
	
	private Date date; 
 	private String error; 
 	private String status; 
 	private String message; 
 	private String exception; 
 	  
 
 

}
