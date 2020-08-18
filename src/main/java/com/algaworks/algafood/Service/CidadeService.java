package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CidadeRepository;
import com.algaworks.algafood.Repository.EstadoRepository;
import com.algaworks.algafood.entity.Cidade;
import com.algaworks.algafood.entity.Estado;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoService;

    public List<Cidade> todas() {
        return repository.findAll();
    }

    public Cidade buscarPorId(Long id) {

        try {

            return repository.findById(id).get();

        } catch (NoSuchElementException e) {
            throw new AlgaFoodResultadoVazioException(String.format("Não foi possível encontrar o cidade de id: %d", id));
        }


    }

    public Cidade salvar(Cidade cidade) {

        Long idEstado = cidade.getEstado().getId();

        Estado estado = estadoService.findById(idEstado)
                                     .orElseThrow(() -> new AlgaFoodResultadoVazioException(String.format("Não foi possível encontrar o estado de id: %d", idEstado)));
        cidade.setEstado(estado);
        return repository.save(cidade);
    }

    public Cidade alterar(Cidade cidade) {
        return this.salvar(cidade);
    }

    public void apagar(Long id) {

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new AlgaFoodRestricaoException(String.format("Não foi possível apagar a cidade de id: %d pois a mesma encontra-se vinculadas a outros relacionamentos", id));
        } catch (EmptyResultDataAccessException e) {
            throw new AlgaFoodResultadoVazioException(String.format("a cidade de id: %d não existe.", id));
        }
    }
}
