package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> todas();

    Cidade porId(Long id);

    Cidade adicionar(Cidade usuario);

    void remover(Cidade permissao);
}
