package com.algaworks.algafood.Repository;

import com.algaworks.algafood.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    List<Restaurante> nome(String nome);
}
