package com.algaworks.algafood.Repository.customizado;

import com.algaworks.algafood.entity.Restaurante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

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
}
