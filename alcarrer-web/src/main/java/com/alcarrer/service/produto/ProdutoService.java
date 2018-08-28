package com.alcarrer.service.produto;

import java.util.List;

import com.alcarrer.model.Produto;

public interface ProdutoService {
	Produto incluir(Produto produto);
	Produto alterar(Produto produto);
	void excluir(Produto produto);
	boolean validarCodigoProduto(Produto produto);
	Produto consultarByCodigo(Produto produto);
	List<Produto> consultar();
	Produto consultarByBarCode(Produto produto);
	
}
