package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.CaixaEntity;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Integer> {

}
