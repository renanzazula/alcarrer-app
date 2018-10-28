package com.alcarrer.controller;

import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alcarrer.model.Error;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus
	@ExceptionHandler(Exception.class)
	public String globalHandleException(Model model, Exception exception){
		Error erro = new Error();
		erro.setDate(new Date());
		erro.setMessage(exception.getMessage());
		model.addAttribute("error",  erro);
		return "erro";
	}
	
}
