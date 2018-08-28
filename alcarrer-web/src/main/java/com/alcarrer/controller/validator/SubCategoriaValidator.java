package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alcarrer.model.SubCategoria;

@Component
public class SubCategoriaValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return SubCategoria.class.equals(clazz);
	}

	 
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.subCategoriaForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.subCategoriaForm.descricao");
	}

}
