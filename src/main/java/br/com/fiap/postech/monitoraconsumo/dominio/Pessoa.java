package br.com.fiap.postech.monitoraconsumo.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Pessoa {

    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;
    private Sexo sexo;
    private Parentesco parentesco;

    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }

    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Pessoa setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public Pessoa setSexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public Pessoa setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
        return this;
    }
}
