package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.DominioEntity;

@Repository
public interface DominioRepository extends JpaRepository<DominioEntity, Integer> {

}
