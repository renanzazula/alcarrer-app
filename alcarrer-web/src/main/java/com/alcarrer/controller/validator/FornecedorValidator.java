package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alcarrer.model.Fornecedor;

@Component
public class FornecedorValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return Fornecedor.class.equals(clazz);
	}
  
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.fornecedorForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.fornecedorForm.descricao");
	}

}
