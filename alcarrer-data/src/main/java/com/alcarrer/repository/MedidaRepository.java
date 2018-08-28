package com.alcarrer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.CategoriaEntity;
import com.alcarrer.entity.MarcaEntity;
import com.alcarrer.entity.MedidaEntity;
import com.alcarrer.entity.SubCategoriaEntity;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, Integer> {

	public List<MedidaEntity> findByItensTipoMedidaCategoriaAndItensTipoMedidaSubCategoriaAndAndItensTipoMedidaMarca(
            CategoriaEntity categoria_codigo, SubCategoriaEntity sub_categoria_codigo, MarcaEntity marca_codigo);

}
