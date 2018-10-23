package com.alcarrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alcarrer.entity.CaixaEntity;

@Repository
public interface CaixaRepository extends JpaRepository<CaixaEntity, Integer> {
	
	@Query(value = "SELECT codigo FROM caixa ORDER BY codigo DESC LIMIT 0, 1", nativeQuery = true)
	Integer gerarCodigoCaixa();

	@Query(value = "SELECT * FROM caixa where status = 'A' ORDER BY codigo DESC LIMIT 0, 1", nativeQuery = true)
	CaixaEntity buscarUltimoCaixa();

	
}
