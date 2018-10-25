package com.alcarrer.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.alcarrer.entity.FormasDePagamentoEntity;
import com.alcarrer.entity.VendaEntity;

public class VendaSpecification implements Specification<VendaEntity> {

	private VendaEntity venda;

	public VendaSpecification(VendaEntity venda) {
		super();
		this.venda = venda;
	}

	@Override
	public Predicate toPredicate(Root<VendaEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		Predicate p = cb.conjunction();

		if (venda.getCodigo() != null) {
			p.getExpressions().add(cb.equal(root.get("codigo"), venda.getCodigo()));
		}

		if (venda.getData() != null) {
			p.getExpressions().add(cb.equal(root.get("data"), venda.getData()));
		}

		if (venda.getStatus() != null) {
			if(!venda.getStatus().equals("NONE")) {
				p.getExpressions().add(cb.equal(root.get("status"), venda.getStatus()));
			}
		}

		// TODO: Cliente

		if (venda.getFormaDePagamento() != null) {
			if (venda.getFormaDePagamento().getCodigo() != null) {
				
				Join<VendaEntity, FormasDePagamentoEntity> sq = root.join("formaDePagamento");
				p.getExpressions().add(cb.equal(sq.get("codigo"), venda.getFormaDePagamento().getCodigo()));
				
			}
		}
		return p;
	}
}
