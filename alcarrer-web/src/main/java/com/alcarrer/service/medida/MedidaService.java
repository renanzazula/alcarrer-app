package com.alcarrer.service.medida;

import java.util.List;

import com.alcarrer.model.Medida;
import com.alcarrer.model.Produto;

public interface MedidaService {

	Medida incluir(Medida objct);

	Medida alterar(Medida objct);

	void excluir(Medida objct);

	List<Medida> consultar();

	Medida consultarByCodigo(Medida objct);

	List<Medida> consultarByProdutoAndValor(Produto produto);

	List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto);

}
