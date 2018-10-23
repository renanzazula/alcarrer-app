package com.alcarrer.service.caixa;

import com.alcarrer.entity.CaixaEntity;
import com.alcarrer.model.Caixa;
import com.alcarrer.model.Venda;

public interface CaixaService {

	Caixa carregarCaixa(Caixa caixa);

	Caixa abrirCaixa(Caixa Caixa);

	Caixa fecharCaixa(Caixa Caixa);

	Caixa buscarUltimoCaixa();

	Caixa buscarCaixa(Caixa Caixa);

	Integer gerarCodigoCaixa();

	Caixa updateValorCaixa(CaixaEntity caixa, Venda venda);
}
