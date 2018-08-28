package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcarrer.model.Dominio;

@Component
public class DominioValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Dominio.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.fornecedorForm.name");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.fornecedorForm.descricao");
	}
}