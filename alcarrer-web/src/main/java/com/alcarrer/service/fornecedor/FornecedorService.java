package com.alcarrer.service.fornecedor;

import java.util.List;

import com.alcarrer.model.Fornecedor;

public interface FornecedorService {

	Fornecedor incluir(Fornecedor objct);

	Fornecedor alterar(Fornecedor objct);
	
	void excluir(Fornecedor objct);
	
	List<Fornecedor> consultar();
	
	Fornecedor consultarByCodigo(Fornecedor  objct);
}
