package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnderecoForm {

    @JsonProperty
    @NotBlank(message = "Rua é um campo obrigatório e não pode estar em branco.")
    private String rua;
    @JsonProperty
    @NotNull(message = "Número não pode ser nulo.")
    private Integer numero;
    @JsonProperty
    @NotBlank(message = "Bairro é um campo obrigatório e não pode estar em branco.")
    private String bairro;
    @JsonProperty
    @NotBlank(message = "Cidade é um campo obrigatório e não pode estar em branco.")
    private String cidade;
    @JsonProperty
    @NotBlank(message = "Estado é um campo obrigatório e não pode estar em branco.")
    private String estado;

    public Endereco toEndereco() {
        return new Endereco().setRua(rua).setBairro(bairro).setCidade(cidade).setEstado(estado).setNumero(numero);
    }

}
