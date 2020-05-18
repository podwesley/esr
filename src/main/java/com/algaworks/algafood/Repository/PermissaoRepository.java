package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> todas();

    Permissao porId(Long id);

    Permissao adicionar(Permissao usuario);

    void remover(Permissao permissao);
}
