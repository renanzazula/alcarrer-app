package com.alcarrer.service.subCategoria;

import java.util.List;

import com.alcarrer.model.SubCategoria;

public interface SubCategoriaService {

	SubCategoria incluir(SubCategoria objc);

	SubCategoria alterar(SubCategoria objc);

	void excluir(SubCategoria objc);
	
	List<SubCategoria> consultar();

	SubCategoria consultarByCodigo(Integer codigo);
	
}
