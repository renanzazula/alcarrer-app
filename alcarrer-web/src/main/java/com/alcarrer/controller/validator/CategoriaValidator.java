package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alcarrer.model.Categoria;

@Component
public class CategoriaValidator implements Validator {


	public boolean supports(Class<?> clazz) {
		return Categoria.class.equals(clazz);
	}
 	 
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.categoriaForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.categoriaForm.descricao");
	}

}
