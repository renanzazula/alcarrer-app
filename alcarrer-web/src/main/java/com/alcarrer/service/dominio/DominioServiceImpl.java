package com.alcarrer.service.dominio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcarrer.entity.DominioEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Dominio;
import com.alcarrer.repository.DominioRepository;

@Service
public class DominioServiceImpl implements DominioService {

	@Autowired
	private DominioRepository repository;

	@Override
	@Transactional
	public Dominio incluir(Dominio entity) {
		DominioEntity dominioDB = new DominioEntity();
		dominioDB.setDescricao(entity.getDescricao());
		dominioDB.setNome(entity.getNome());
		return JpaFunctions.dominioDTOtoDominio.apply(repository.save(dominioDB));
	}

	@Override
	@Transactional
	public Dominio alterar(Dominio entity) {
		DominioEntity dominioDB = repository.getOne(entity.getCodigo());
		dominioDB.setDescricao(entity.getDescricao());
		dominioDB.setNome(entity.getNome());
		return JpaFunctions.dominioDTOtoDominio.apply(repository.saveAndFlush(dominioDB));
	}

	@Override
	@Transactional
	public void excluir(Dominio entity) {
		DominioEntity dominioDB = repository.getOne(entity.getCodigo());
		// dominioDB.setStatus(Status.Inativo);
		repository.delete(dominioDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Dominio> consultar() {
		return repository.findAll().stream().map(JpaFunctions.dominioDTOtoDominio).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Dominio consultarByCodigo(Dominio dominio) {
		return JpaFunctions.dominioDTOtoDominio.apply(repository.findOne(dominio.getCodigo()));
	}

}
