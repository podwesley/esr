package com.algaworks.algafood.Repository.customizado;

import com.algaworks.algafood.entity.Restaurante;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryCustomImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        String jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurante.class)
                      .setParameter("nome", "%" + nome + "%")
                      .setParameter("taxaInicial", taxaFreteInicial)
                      .setParameter("taxaFinal", taxaFreteFinal)
                      .getResultList();
    }

    @Override
    public List<Restaurante> findDinamico(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 ");

        HashMap<String, Object> parametros = new HashMap<>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = manager
                .createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((key, value) -> {
            query.setParameter(key, value);
        });

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComCriteriaAPI(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> rootRestaurante = criteriaQuery.from(Restaurante.class);

        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome))
            predicates.add(criteriaBuilder.like(rootRestaurante.get("nome"), "%" + nome + "%"));

        if (taxaFreteInicial != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(rootRestaurante.get("taxaFrete"), taxaFreteInicial));

        if (taxaFreteFinal != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(rootRestaurante.get("taxaFrete"), taxaFreteFinal));

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }

}
