package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.SubCategoriaEntity;

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoriaEntity, Integer> {

}
