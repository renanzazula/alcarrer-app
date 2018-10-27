package com.alcarrer.service.caixa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcarrer.entity.CaixaEntity;
import com.alcarrer.enums.StatusCaixaEnum;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Caixa;
import com.alcarrer.model.Venda;
import com.alcarrer.repository.CaixaRepository;

@Service
public class CaixaServiceImpl implements CaixaService {

	@Autowired
	private CaixaRepository repository;

	@Override
	@Transactional
	public Caixa carregarCaixa(Caixa caixa) {
		return JpaFunctions.caixaToCaixaEntity.apply(repository.getOne(caixa.getCodigo()));
	}

	@Override
	@Transactional
	public Caixa abrirCaixa(Caixa caixa) {
		CaixaEntity caixaEntity = new CaixaEntity();
		caixaEntity.setValorInicial(caixa.getValorInicial());
		caixaEntity.setValorFinal(new Double(0));
		caixaEntity.setTotal(new Double(0));
		caixaEntity.setTotalDesconto(new Double(0));
		caixaEntity.setTotalVendas(new Double(0));
		caixaEntity.setStatus(StatusCaixaEnum.A);
		caixaEntity.setDataAbertura(new Date());
		caixaEntity.setHoraAbertura(new Date());
		return  JpaFunctions.caixaToCaixaEntity.apply(repository.saveAndFlush(caixaEntity));
	}

	@Override
	@Transactional
	public Caixa fecharCaixa(Caixa caixa) {
		CaixaEntity caixaEntity = repository.getOne(caixa.getCodigo());
		caixaEntity.setStatus(StatusCaixaEnum.F);
		caixaEntity.setDataFechamento(new Date());
		caixaEntity.setHoraFechamento(new Date());
		return  JpaFunctions.caixaToCaixaEntity.apply(repository.saveAndFlush(caixaEntity));
	}	

	@Override
	@Transactional
	public Caixa buscarUltimoCaixa() {
		CaixaEntity caixaEntity = repository.buscarUltimoCaixa();
 		if(caixaEntity != null) {
 			return JpaFunctions.caixaToCaixaEntity.apply(caixaEntity);
 		} else {
 			Caixa caixa = new Caixa(); 
			caixa.setCodigo(gerarCodigoCaixa());
			caixa.setDataAbertura(new java.util.Date());
			caixa.setHoraAbertura(new java.util.Date());
			caixa.setValorInicial(new Double(0));
			caixa.setValorFinal(new Double(0));
			caixa.setTotalVendas(new Double(0));
			caixa.setTotalDesconto(new Double(0));
			caixa.setTotal(new Double(0));			
			caixa.setStatus(StatusCaixaEnum.F.toString());
			return caixa;
 		}
	}

	@Override
	@Transactional
	public Caixa buscarCaixa(Caixa caixa) {
		return JpaFunctions.caixaToCaixaEntity.apply(repository.getOne(caixa.getCodigo()));
	}

	@Override
	@Transactional
	public Caixa updateValorCaixa(CaixaEntity caixa, Venda venda) {
		CaixaEntity caixaEntity = repository.getOne(caixa.getCodigo());		
		
		caixaEntity.setTotalDesconto(caixaEntity.getTotalDesconto() + venda.getDesconto() );
		Double totalVendas = caixaEntity.getTotalVendas() + venda.getValorPago();
		caixaEntity.setTotalVendas(totalVendas);
		
		// FIXME: arrumar melhor solucao 
		if(caixa.getValorInicial() == null) {
			caixaEntity.setTotal(totalVendas + new Double(0));	
		}else {
			caixaEntity.setTotal(totalVendas + caixa.getValorInicial());
		}
		
		return JpaFunctions.caixaToCaixaEntity.apply(repository.saveAndFlush(caixaEntity));
	}
		
	@Override
	@Transactional
	public Integer gerarCodigoCaixa() {
		Integer codigo = repository.gerarCodigoCaixa();
		if(codigo != null) {
			codigo = codigo +1;
		}else {
			codigo = 0; 
		}			  
		return codigo;  
	}

}
