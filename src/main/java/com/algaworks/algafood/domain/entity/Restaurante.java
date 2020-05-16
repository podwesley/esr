package com.algaworks.algafood.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_restaurante")
    private String nome;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    //Muitos restaurantes possui uma cozinha.
    @ManyToOne
    private Cozinha cozinha;
}
