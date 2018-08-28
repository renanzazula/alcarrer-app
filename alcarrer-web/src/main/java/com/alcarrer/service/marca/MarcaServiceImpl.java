package com.alcarrer.service.marca;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcarrer.entity.MarcaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Marca;
import com.alcarrer.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository repository;

	@Override
	@Transactional
	public Marca incluir(Marca entity) {
		MarcaEntity marcaDB = new MarcaEntity();
		marcaDB.setDescricao(entity.getDescricao());
		marcaDB.setNome(entity.getNome());
		return JpaFunctions.marcaDTOtomarca.apply(repository.save(marcaDB));
	}

	@Override
	@Transactional
	public Marca alterar(Marca entity) {
		MarcaEntity marcaDB = repository.getOne(entity.getCodigo());
		marcaDB.setDescricao(entity.getDescricao());
		marcaDB.setNome(entity.getNome());
		return JpaFunctions.marcaDTOtomarca.apply(repository.saveAndFlush(marcaDB));
	}

	@Override
	@Transactional
	public void excluir(Marca entity) {
		MarcaEntity marcaDB = repository.getOne(entity.getCodigo());
		// marcaDB.setStatus(Status.Inativo);
		repository.delete(marcaDB);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Marca> consultar() {
		return repository.findAll().stream().map(JpaFunctions.marcaDTOtomarca).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Marca consultarByCodigo(Marca marca) {
		return JpaFunctions.marcaDTOtomarca.apply(repository.findOne(marca.getCodigo()));
	}

}
