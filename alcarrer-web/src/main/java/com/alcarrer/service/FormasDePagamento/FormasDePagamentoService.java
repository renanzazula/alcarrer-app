package com.alcarrer.service.FormasDePagamento;

import java.util.List;

import com.alcarrer.model.FormasDePagamento;

public interface FormasDePagamentoService {

	FormasDePagamento incluir(FormasDePagamento objct);

	FormasDePagamento alterar(FormasDePagamento objct);
	
	void excluir(FormasDePagamento objct);
	
	List<FormasDePagamento> consultar();
	
	FormasDePagamento consultarByCodigo(FormasDePagamento objct);
	
}
