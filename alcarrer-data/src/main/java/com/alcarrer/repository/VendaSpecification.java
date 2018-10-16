package com.alcarrer.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alcarrer.entity.VendaEntity;

public class VendaSpecification implements Specification<VendaEntity>{

	@Override
	public Predicate toPredicate(Root<VendaEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		 
		return null;
	}

}
