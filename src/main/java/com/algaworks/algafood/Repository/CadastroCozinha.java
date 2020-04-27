package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Cozinha;
import com.algaworks.algafood.domain.entity.Restaurante;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {
        return entityManager.createQuery("from Cozinha",Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha adicionar (Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    public Cozinha buscarPeloID(Long id) {
        return entityManager.find(Cozinha.class , id);
    }

}
