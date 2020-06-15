package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CozinhaRepository;
import com.algaworks.algafood.Repository.EstadoRepository;
import com.algaworks.algafood.entity.Cozinha;
import com.algaworks.algafood.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> todos() {
        return repository.findAll();
    }

    public Estado buscarPorId(Long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }

}
