package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcarrer.model.Venda;

@Component
public class VendaValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Venda.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo",
		// "NotEmpty.produtoForm.codigo");
	}

}
