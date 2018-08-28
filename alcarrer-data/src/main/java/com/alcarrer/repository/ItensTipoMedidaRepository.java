package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alcarrer.entity.ItensTipoMedidaEntity;

public interface ItensTipoMedidaRepository extends JpaRepository<ItensTipoMedidaEntity, Integer>{
	
	ItensTipoMedidaEntity findByMedidaCodigo(Integer medida_codigo);
	
}
