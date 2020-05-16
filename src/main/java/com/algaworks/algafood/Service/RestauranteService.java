package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CozinhaRepository;
import com.algaworks.algafood.Repository.RestauranteRepository;
import com.algaworks.algafood.domain.entity.Cozinha;
import com.algaworks.algafood.domain.entity.Restaurante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService implements RestauranteRepository {


    @Override
    public List<Restaurante> todas() {
        return null;
    }

    @Override
    public Restaurante porId(Long id) {
        return null;
    }

    @Override
    public Restaurante adicionar(Restaurante cozinha) {
        return null;
    }

    @Override
    public void remover(Restaurante cozinha) {

    }
}
