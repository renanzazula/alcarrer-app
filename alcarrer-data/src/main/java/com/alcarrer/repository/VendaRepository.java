package com.alcarrer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.VendaEntity;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer>, JpaSpecificationExecutor<VendaEntity> {

	default List<VendaEntity> filter(VendaEntity venda) {
		return findAll(new VendaSpecification(venda));
	}

}
