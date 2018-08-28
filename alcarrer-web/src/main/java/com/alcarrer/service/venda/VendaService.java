package com.alcarrer.service.venda;

import java.util.List;

import com.alcarrer.model.Venda;

public interface VendaService {
	Venda incluir(Venda venda);

	Venda alterar(Venda venda);

	void excluir(Venda venda);

	Venda consultarByCodigo(Venda venda);

	List<Venda> consultar();

	long gerarCodigoVenda();
}
