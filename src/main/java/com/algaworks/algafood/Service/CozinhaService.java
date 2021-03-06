package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CozinhaRepository;
import com.algaworks.algafood.entity.Cozinha;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;

    //TODO - Remover esse lixo.
    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> todas() {
        return repository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public Cozinha alterar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    /**
     * Busca uma Entidade por nome passando um paramentro para tal.
     * @param nome
     * @return
     */
    public List<Cozinha> buscarPorNome(String nome) {
        return entityManager.createQuery("FROM Cozinha WHERE nome LIKE :nome", Cozinha.class).setParameter("nome", "%" + nome + "%").getResultList();
    }

    public void apagar(Long id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AlgaFoodRestricaoException(String.format("Não foi possível apagar a cozinha de id: %d pois a mesma encontra-se vinculadas a outros relacionamentos", id));
        } catch (EmptyResultDataAccessException e) {
            throw new AlgaFoodResultadoVazioException(String.format("a cozinha de id: %d não existe.", id));
        }
    }

    public boolean verificarSeCozinhaExiste(String nomeCozinha) {
        return repository.existsByNome(nomeCozinha);
    }

    public Optional<Cozinha> buscarPrimeiro(){
        return repository.buscarPrimeiro();
    }
}
