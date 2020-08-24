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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    public List<Restaurante> buscarRestaurantesPorIntervaloDeTaxaFrete(BigDecimal txInicial, BigDecimal txFinal) {
        return repository.findByTaxaFreteBetween(txInicial, txFinal);
    }

    public List<Restaurante> buscarPorNome(String nome) {
        return repository.findBynome(nome);
    }

    public List<Restaurante> buscarPorParteDoNome(String parteNome) {
        return repository.findByNomeContaining(parteNome);
    }

    public List<Restaurante> buscarPorNomeRestauranteIdCozinhaa(String nome, Long cozinha) {
        return repository.findByNomeContainingAndCozinhaId(nome, cozinha);
    }

    public Optional<Restaurante> buscarPrimeiroRestaurantePesquisa(String nome) {
        return repository.findFirstByNomeContaining(nome);
    }

    public List<Restaurante> buscarPrimeiros2RestaurantesQueContem(String nome) {
        return repository.findTop2ByNomeContaining(nome);
    }

    public int restaurantesPorCozinhaId(Long cozinhaId) {
        return repository.countByCozinhaId(cozinhaId);
    }

    public List<Restaurante> consultarPorNome(String nome, Long id) {
        return repository.consultarPorNome(nome, id);
    }

    public List<Restaurante> consultarPorNome2(String nome, Long id) {
        return repository.consultarPorNome2(nome, id);
    }

    public List<Restaurante> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return repository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }
}
