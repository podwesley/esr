package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CozinhaRepository;
import com.algaworks.algafood.entity.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> todas() {
        return repository.findAll();
    }

    public Cozinha buscarPorId(Long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }

    public Cozinha salvar (Cozinha cozinha){
        return repository.save(cozinha);
    }

    public Cozinha alterar(Cozinha cozinha) {
        return repository.save(cozinha);
    }
}
