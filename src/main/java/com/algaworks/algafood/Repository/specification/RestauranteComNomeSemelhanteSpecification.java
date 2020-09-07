package com.algaworks.algafood.Repository.specification;

import com.algaworks.algafood.entity.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestauranteComNomeSemelhanteSpecification implements Specification<Restaurante> {

    private static final long serialVersionUID = 4825484437222284411L;
    private String nome;

    public RestauranteComNomeSemelhanteSpecification(String nome) {
        this.nome = nome;
    }

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
