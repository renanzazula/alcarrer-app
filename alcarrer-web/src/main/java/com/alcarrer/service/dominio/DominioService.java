package com.alcarrer.service.dominio;

import java.util.List;

import com.alcarrer.model.Dominio;

public interface DominioService {

	Dominio incluir(Dominio objct);

	Dominio alterar(Dominio objct);

	void excluir(Dominio objct);

	List<Dominio> consultar();

	Dominio consultarByCodigo(Dominio dominio);
	
}
