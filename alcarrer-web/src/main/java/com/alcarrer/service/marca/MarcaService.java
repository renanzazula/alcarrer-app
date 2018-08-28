package com.alcarrer.service.marca;

import java.util.List;

import com.alcarrer.model.Marca;

public interface MarcaService {

	Marca incluir(Marca objct);

	Marca alterar(Marca objct);

	void excluir(Marca objct);

	List<Marca> consultar();

	Marca consultarByCodigo(Marca marca);
}
