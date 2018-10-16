package com.alcarrer.service.subCategoria;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alcarrer.entity.SubCategoriaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.repository.SubCategoriaRepository;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

	@Autowired
	private SubCategoriaRepository repository;

	@Override
	public SubCategoria incluir(SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = new SubCategoriaEntity();
		subCategoriaDB.setNome(entity.getNome());
		subCategoriaDB.setDescricao(entity.getDescricao());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	public SubCategoria alterar(SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = repository.findOne(entity.getCodigo());
		subCategoriaDB.setDescricao(entity.getDescricao());
		subCategoriaDB.setNome(entity.getNome());
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.saveAndFlush(subCategoriaDB));
	}

	@Override
	public void excluir(SubCategoria entity) {
		SubCategoriaEntity subCategoriaDB = repository.findOne(entity.getCodigo());
		repository.delete(subCategoriaDB);
	}

	@Override
	public List<SubCategoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.subCategoriaToSubCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	public SubCategoria consultarByCodigo(Integer codigo) {
		return JpaFunctions.subCategoriaToSubCategoriaEntity.apply(repository.findOne(codigo));
	}

}
