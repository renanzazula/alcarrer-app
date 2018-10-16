package com.alcarrer.service.fornecedor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alcarrer.entity.FornecedorEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Fornecedor;
import com.alcarrer.repository.FornecedorRepository;

@Service
public class FornecedorServiceImpl implements FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	@Override
	public Fornecedor incluir(Fornecedor entity) {
		FornecedorEntity fornecedorDB = new FornecedorEntity();
		fornecedorDB.setDescricao(entity.getDescricao());
		fornecedorDB.setNome(entity.getNome());
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
	}

	@Override
	public Fornecedor alterar(Fornecedor entity) {
		FornecedorEntity fornecedorDB = repository.findOne(entity.getCodigo());
	 	fornecedorDB.setDescricao(entity.getDescricao());
		fornecedorDB.setNome(entity.getNome());
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.saveAndFlush(fornecedorDB));
	}

	@Override
	public void excluir(Fornecedor entity) {
		FornecedorEntity fornecedorDB = repository.findOne(entity.getCodigo());
		repository.delete(fornecedorDB);
	}

	@Override
	public List<Fornecedor> consultar() {
		return repository.findAll().stream().map(JpaFunctions.fornecedortoFornecedorEntity).collect(Collectors.toList());
	}

	@Override
	public Fornecedor consultarByCodigo(Fornecedor entity) {
		return JpaFunctions.fornecedortoFornecedorEntity.apply(repository.findOne(entity.getCodigo()));
	}

}
