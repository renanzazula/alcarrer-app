package com.alcarrer.service.FormasDePagamento;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alcarrer.entity.FormasDePagamentoEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.FormasDePagamento;
import com.alcarrer.repository.FormaDePagamentoRepository;

@Service
public class FormasDePagamentoServiceImpl implements FormasDePagamentoService {

	@Autowired
	FormaDePagamentoRepository formaDePagamentoRepository;

	@Override
	public FormasDePagamento incluir(FormasDePagamento objct) {
		FormasDePagamentoEntity formasDePagamentoDB = new FormasDePagamentoEntity();
		formasDePagamentoDB.setNome(objct.getNome());
		formasDePagamentoDB.setDescricao(objct.getDescricao());
		formasDePagamentoDB.setPorcentagemDesconto(objct.getPorcentagemDesconto());
		formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB);
		return JpaFunctions.formasDePagamentoToFormasDePagamentoEntity
				.apply(formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB));
	}

	@Override
	public FormasDePagamento alterar(FormasDePagamento objct) {
		FormasDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.findOne(objct.getCodigo());
		formasDePagamentoDB.setNome(objct.getNome());
		formasDePagamentoDB.setDescricao(objct.getDescricao());
		formasDePagamentoDB.setPorcentagemDesconto(objct.getPorcentagemDesconto());
		formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB);
		return JpaFunctions.formasDePagamentoToFormasDePagamentoEntity
				.apply(formaDePagamentoRepository.saveAndFlush(formasDePagamentoDB));
	}

	@Override
	public void excluir(FormasDePagamento objct) {
		FormasDePagamentoEntity formasDePagamentoDB = formaDePagamentoRepository.findOne(objct.getCodigo());
		formaDePagamentoRepository.delete(formasDePagamentoDB);
	}

	@Override
	public List<FormasDePagamento> consultar() {
		return formaDePagamentoRepository.findAll().stream().map(JpaFunctions.formasDePagamentoToFormasDePagamentoEntity)
				.collect(Collectors.toList());
	}

	@Override
	public FormasDePagamento consultarByCodigo(FormasDePagamento objct) {
		return JpaFunctions.formasDePagamentoToFormasDePagamentoEntity
				.apply(formaDePagamentoRepository.findOne(objct.getCodigo()));
	}

}
