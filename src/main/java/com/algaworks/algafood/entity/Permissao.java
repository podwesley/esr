package com.algaworks.algafood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Permissao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("permissão")
    @Column(name = "permissao_nome", nullable = false)
    private String nome;


    @Column(name = "permissao_descricao", nullable = false)
    private String descricao;
}
