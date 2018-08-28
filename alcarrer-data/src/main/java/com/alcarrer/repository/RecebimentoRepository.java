package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.RecebimentoEntity;

@Repository
public interface RecebimentoRepository extends JpaRepository<RecebimentoEntity, Integer> {

}
