package com.algaworks.algafood.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_cozinha")
    private String nome;
    
}
