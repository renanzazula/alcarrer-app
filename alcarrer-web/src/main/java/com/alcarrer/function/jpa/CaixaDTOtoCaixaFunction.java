package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.CaixaEntity;
import com.alcarrer.model.Caixa;

public class CaixaDTOtoCaixaFunction implements Function<CaixaEntity, Caixa> {

	@Override
	public Caixa apply(CaixaEntity input) {
		Caixa output = new Caixa();
		output.setCodigo(input.getCodigo());
		output.setDataHoraAbertura(input.getDataHoraAbertura());
		output.setDataHoraFechamento(input.getDataHoraFechamento());
		output.setValorInicial(input.getValorInicial());
		output.setValorFinal(input.getValorFinal());
		output.setTotal(input.getTotal());
		output.setStatusCaixa(input.getStatusCaixa());
		return output;
	}

}
