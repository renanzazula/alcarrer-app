package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.MarcaEntity;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {

}
