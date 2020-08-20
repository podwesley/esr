package com.algaworks.algafood.Repository;

import com.algaworks.algafood.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    boolean existsByNome(String nomeCozinha);
}
