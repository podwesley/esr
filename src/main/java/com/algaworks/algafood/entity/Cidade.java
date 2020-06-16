package com.algaworks.algafood.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("cidade")
    @Column(name = "nome_cidade")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id" ,nullable = false)
    private Estado estado;
}
