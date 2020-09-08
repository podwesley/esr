package com.algaworks.algafood.Repository;

import com.algaworks.algafood.Repository.customizado.RestauranteRepositoryCustom;
import com.algaworks.algafood.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>, RestauranteRepositoryCustom, JpaSpecificationExecutor<Cozinha> {

    boolean existsByNome(String nomeCozinha);
}
