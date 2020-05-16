package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository {

    List<Cozinha> todas();

    Cozinha porId(Long id);

    Cozinha adicionar(Cozinha cozinha);

    void remover(Cozinha cozinha);

}
