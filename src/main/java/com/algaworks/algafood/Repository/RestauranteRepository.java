package com.algaworks.algafood.Repository;

import com.algaworks.algafood.domain.entity.Cozinha;
import com.algaworks.algafood.domain.entity.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> todas();

    Restaurante porId(Long id);

    Restaurante adicionar(Restaurante cozinha);

    void remover(Restaurante cozinha);

}
