package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CozinhaRepository;
import com.algaworks.algafood.Repository.RestauranteRepository;
import com.algaworks.algafood.entity.Cozinha;
import com.algaworks.algafood.entity.Restaurante;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> todas() {
        return repository.findAll();
    }

    public Restaurante buscarPorId(Long id) {

            return repository.findById(id)
                             .orElseThrow(() -> new AlgaFoodResultadoVazioException(String.format("Não foi possível encontrar o restaurante de id: %d", id)));
    }

    public Restaurante salvar(Restaurante restaurante) {

        Long idCozinha = restaurante.getCozinha().getId();

        Cozinha cozinha = this.cozinhaRepository.findById(idCozinha)
                                                .orElseThrow(() -> new AlgaFoodResultadoVazioException(String.format("Não foi possível encontrar a cozinha de id: %d", idCozinha)));

        restaurante.setCozinha(cozinha);
        return repository.save(restaurante);
    }

    public Restaurante alterar(Restaurante restaurante) {
        return this.salvar(restaurante);
    }

    public void apagar(Long id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AlgaFoodRestricaoException(String.format("Não foi possível apagar o restaurante de id: %d pois o mesma encontra-se vinculados a outros relacionamentos", id));
        } catch (EmptyResultDataAccessException e) {
            throw new AlgaFoodResultadoVazioException(String.format("a restaurante de id: %d não existe.", id));
        }
    }


}
