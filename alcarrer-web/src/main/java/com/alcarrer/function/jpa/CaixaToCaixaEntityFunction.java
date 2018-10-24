package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.CaixaEntity;
import com.alcarrer.model.Caixa;

public class CaixaToCaixaEntityFunction implements Function<CaixaEntity, Caixa> {

	@Override
	public Caixa apply(CaixaEntity input) {
		Caixa output = new Caixa();		
		output.setCodigo(input.getCodigo());
		output.setDataAbertura(input.getDataAbertura());
		output.setHoraAbertura(input.getHoraAbertura());		
		output.setDataFechamento(input.getDataFechamento());
		output.setHoraFechamento(input.getHoraFechamento());		
		output.setValorInicial(input.getValorInicial());
		output.setValorFinal(input.getValorFinal());
		output.setTotal(input.getTotal());
		output.setTotalVendas(input.getTotalVendas());
		output.setTotalDesconto(input.getTotalDesconto());
		output.setStatus(input.getStatus().name());		
		return output;
	}

}
