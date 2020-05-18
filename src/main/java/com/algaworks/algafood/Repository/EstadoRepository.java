package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> todas();

    Estado porId(Long id);

    Estado adicionar(Estado usuario);

    void remover(Estado permissao);
}
