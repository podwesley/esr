package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.EstadoRepository;
import com.algaworks.algafood.entity.Estado;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;


    public List<Estado> todas() {
        return repository.findAll();
    }

    public Estado buscarPorId(Long id) {
      return  repository.findById(id)
                        .orElseThrow(() -> new AlgaFoodResultadoVazioException(String.format("Não foi possível encontrar o estado de id: %d", id)));
    }

    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    public Estado alterar(Estado estado) {
        return this.salvar(estado);
    }

    public void apagar(Long id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AlgaFoodRestricaoException(String.format("Não foi possível apagar o estado de id: %d pois o mesma encontra-se vinculados a outros relacionamentos", id));
        } catch (EmptyResultDataAccessException e) {
            throw new AlgaFoodResultadoVazioException(String.format("a estado de id: %d não existe.", id));
        }
    }


}
