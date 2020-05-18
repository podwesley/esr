package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> todas();

    Cozinha porId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}
