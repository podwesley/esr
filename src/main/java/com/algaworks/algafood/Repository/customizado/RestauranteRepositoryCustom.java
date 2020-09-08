package com.algaworks.algafood.Repository.customizado;

import com.algaworks.algafood.entity.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findDinamico(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findComCriteriaAPI(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}
