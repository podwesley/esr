package com.algaworks.algafood.Repository;

import com.algaworks.algafood.Repository.customizado.RestauranteRepositoryCustom;
import com.algaworks.algafood.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom, JpaSpecificationExecutor<Restaurante> {

    List<Restaurante> findBynome(String nome);

    List<Restaurante> findByNomeContaining(String nome);

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    //Ítem 5.9
    @Query("from Restaurante where nome like %:nome% and cozinha.id = :idCozinha")
    List<Restaurante> consultarPorNome(String nome, Long idCozinha);

    List<Restaurante> consultarPorNome2(String nome, Long idCozinha);

    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long id);

    List<Restaurante> findComCriteriaAPI(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
