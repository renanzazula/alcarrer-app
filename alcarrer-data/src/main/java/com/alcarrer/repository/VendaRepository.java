package com.alcarrer.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.VendaEntity;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer> {
	
	public default List<VendaEntity> filter(VendaEntity venda){

		 
		return null;
	}
	
 
}
