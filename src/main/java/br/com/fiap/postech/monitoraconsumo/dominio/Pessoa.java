package br.com.fiap.postech.monitoraconsumo.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Pessoa {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private LocalDate dataDeNascimento;

    @JsonProperty
    private Sexo sexo;

    @JsonProperty
    private Parentesco parentesco;

}
