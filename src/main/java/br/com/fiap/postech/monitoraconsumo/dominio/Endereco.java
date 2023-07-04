package br.com.fiap.postech.monitoraconsumo.dominio;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Entity
@Table(name="tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco setId(UUID id) {
        this.id = id;
        return this;
    }

    public Endereco setRua(String rua) {
        this.rua = rua;
        return this;
    }

    public Endereco setNumero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public Endereco setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public Endereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public Endereco setEstado(String estado) {
        this.estado = estado;
        return this;
    }
}
