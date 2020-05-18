package com.algaworks.algafood.Repository;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamentoRepository> todas();

    FormaPagamentoRepository porId(Long id);

    FormaPagamentoRepository adicionar(FormaPagamentoRepository usuario);

    void remover(FormaPagamentoRepository permissao);
}
