package com.alcarrer.service.categoria;

import java.util.List;
import java.util.Map;

import com.alcarrer.model.Categoria;

public interface CategoriaService {

	Categoria incluir(Categoria entity);

	Categoria alterar(Categoria entity);
 
	Categoria consultarByCodigo(Categoria entity);

	Map<Long, Categoria> consultaCategoriaSubCategoria();
	
	List<Categoria> consultar();

	void excluir(Categoria entity);
}
