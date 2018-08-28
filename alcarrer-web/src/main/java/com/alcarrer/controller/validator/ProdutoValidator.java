package com.alcarrer.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.alcarrer.model.Produto;

@Component
public class ProdutoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return Produto.class.equals(clazz);
	}

	 
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "NotEmpty.produtoForm.codigo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.produtoForm.nome");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "NotEmpty.produtoForm.descricao");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "porcentagem", "NotEmpty.produtoForm.porcentagem");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precoCusto", "NotEmpty.produtoForm.precoCusto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precoVenda", "NotEmpty.produtoForm.precoVenda");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "porcentagemDesconto", "NotEmpty.produtoForm.porcentagemDesconto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "desconto", "NotEmpty.produtoForm.desconto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precoOferta", "NotEmpty.produtoForm.precoOferta");
		 
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fornecedores", "NotEmpty.produtoForm.fornecedor");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "marcas", "NotEmpty.produtoForm.marca");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categorias", "NotEmpty.produtoForm.categoria");
	}

}