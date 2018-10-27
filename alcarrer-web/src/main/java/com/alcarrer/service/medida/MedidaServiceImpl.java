package com.alcarrer.service.medida;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alcarrer.entity.CategoriaEntity;
import com.alcarrer.entity.ItensTipoMedidaEntity;
import com.alcarrer.entity.MarcaEntity;
import com.alcarrer.entity.MedidaEntity;
import com.alcarrer.entity.SubCategoriaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Medida;
import com.alcarrer.model.Produto;
import com.alcarrer.repository.CategoriaRepository;
import com.alcarrer.repository.MarcaRepository;
import com.alcarrer.repository.MedidaRepository;
import com.alcarrer.repository.SubCategoriaRepository;

@Service
public class MedidaServiceImpl implements MedidaService {

	@Autowired
	private MedidaRepository medidaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Override
	public Medida incluir(Medida medida) {
		MedidaEntity medidaDB = new MedidaEntity();
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
			medida.getItensTipoMedida().forEach(itensMedida -> {
				ItensTipoMedidaEntity itens = new ItensTipoMedidaEntity();
				itens.setCategoria(categoriaRepository.getOne(medida.getCategoria().getCodigo()));
				itens.setSubCategoria(subCategoriaRepository.getOne(medida.getSubCategoria().getCodigo()));
				if (medida.getMarca() != null) {
					itens.setMarca(marcaRepository.getOne(medida.getMarca().getCodigo()));
				}
				itens.setValor(itensMedida.getValor());
				itensSet.add(itens);
			});
			medidaDB.setItensTipoMedida(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	@Override
	public Medida alterar(Medida medida) {
		MedidaEntity medidaDB = medidaRepository.findOne(medida.getCodigo());
		medidaDB.setDescricao(medida.getDescricao());
		medidaDB.setNome(medida.getNome());
		medidaDB.getItensTipoMedida().clear();
		if (medida.getItensTipoMedida() != null) {
			Set<ItensTipoMedidaEntity> itensSet = new HashSet<>();
			medida.getItensTipoMedida().forEach(itensMedida -> {
				ItensTipoMedidaEntity itens = new ItensTipoMedidaEntity();
				itens.setCategoria(categoriaRepository.getOne(medida.getCategoria().getCodigo()));
				itens.setSubCategoria(subCategoriaRepository.getOne(medida.getSubCategoria().getCodigo()));
				if (medida.getMarca() != null) {
					itens.setMarca(marcaRepository.getOne(medida.getMarca().getCodigo()));
				}
				itens.setValor(itensMedida.getValor());
				itensSet.add(itens);
			});
			medidaDB.getItensTipoMedida().addAll(itensSet);
		}
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.saveAndFlush(medidaDB));
	}

	@Override
	public void excluir(Medida objct) {
		MedidaEntity medidaDB = medidaRepository.findOne(objct.getCodigo());
		medidaRepository.delete(medidaDB);
	}

	@Override
	public List<Medida> consultar() {
		return medidaRepository.findAll().stream().map(JpaFunctions.medidaToMedidaEntity).collect(Collectors.toList());
	}

	@Override
	public Medida consultarByCodigo(Medida entity) {
		return JpaFunctions.medidaToMedidaEntity.apply(medidaRepository.findOne(entity.getCodigo()));
	}

	@Override
	public List<Medida> consultarByProdutoAndValor(Produto produto) {

		return null;
	}

	@Override
	public List<Medida> consultarByCategoriaSubCategoriaMarca(Produto produto) {

		CategoriaEntity categoria = null;
		SubCategoriaEntity subCategoria = null;
		MarcaEntity marca = null;

		if (produto.getMarca() != null && produto.getMarca().getCodigo() != null) {
			marca = marcaRepository.findOne(produto.getMarca().getCodigo());
		}

		if (produto.getCategoria().getSubCategorias() != null && produto.getSubCategoria().getCodigo() != null) {
			subCategoria = subCategoriaRepository.findOne(produto.getSubCategoria().getCodigo());
		}

		if (produto.getCategoria() != null && produto.getCategoria().getCodigo() != null) {
			categoria = categoriaRepository.findOne(produto.getCategoria().getCodigo());
		}

		return medidaRepository
				.findByItensTipoMedidaCategoriaAndItensTipoMedidaSubCategoriaAndAndItensTipoMedidaMarca(categoria,
						subCategoria, marca)
				.stream().map(JpaFunctions.medidaToMedidaEntity).collect(Collectors.toList());

	}
	
	 
}
