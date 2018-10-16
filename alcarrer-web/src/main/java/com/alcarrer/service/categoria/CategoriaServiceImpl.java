package com.alcarrer.service.categoria;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcarrer.entity.CategoriaEntity;
import com.alcarrer.entity.SubCategoriaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Categoria;
import com.alcarrer.repository.CategoriaRepository;
import com.alcarrer.repository.SubCategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	@Override
	@Transactional
	public Categoria incluir(Categoria categoria) {
		CategoriaEntity categoriaDB = new CategoriaEntity();
		categoriaDB.setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		
		if(categoria.getSubCategorias() != null) {
			Set<SubCategoriaEntity> subCategoria = new HashSet<>();
			categoria.getSubCategorias().forEach(sub -> {
				subCategoria.add(subCategoriaRepository.findOne(sub.getCodigo()));
			});
			categoriaDB.setSubCategoriasSet(subCategoria);
		}
		return JpaFunctions.categoriatoCategoriaEntity.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional
	public Categoria alterar(Categoria categoria) {

		CategoriaEntity categoriaDB = repository.findOne(categoria.getCodigo());
		categoriaDB.setDescricao(categoria.getDescricao());
		categoriaDB.setNome(categoria.getNome());
		categoriaDB.getSubCategoriasSet().clear();
		Set<SubCategoriaEntity> subCategoria = new HashSet<>();
		categoria.getSubCategorias().forEach(sub -> {
			subCategoria.add(subCategoriaRepository.findOne(sub.getCodigo()));
		});
		categoriaDB.getSubCategoriasSet().addAll(subCategoria);

		return JpaFunctions.categoriatoCategoriaEntity.apply(repository.saveAndFlush(categoriaDB));
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria consultarByCodigo(Categoria entity) {
		return JpaFunctions.categoriatoCategoriaEntity.apply(repository.findOne(entity.getCodigo()));
	}

	@Override
	@Transactional(readOnly = true)
	public Map<Long, Categoria> consultaCategoriaSubCategoria() {

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> consultar() {
		return repository.findAll().stream().map(JpaFunctions.categoriatoCategoriaEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void excluir(Categoria entity) {
		CategoriaEntity categoriaDB = repository.findOne(entity.getCodigo());
		categoriaDB.getSubCategoriasSet().clear();
		repository.saveAndFlush(categoriaDB);
		repository.delete(repository.findOne(entity.getCodigo()));
	}

}
