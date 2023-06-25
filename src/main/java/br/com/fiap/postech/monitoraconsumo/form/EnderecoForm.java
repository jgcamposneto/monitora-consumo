package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoForm {

    @JsonProperty
    private String rua;
    @JsonProperty
    private Integer numero;
    @JsonProperty
    private String bairro;
    @JsonProperty
    private String cidade;
    @JsonProperty
    private String estado;

    public Endereco toEndereco() {
        return new Endereco().setRua(rua).setBairro(bairro).setCidade(cidade).setEstado(estado).setNumero(numero);
    }

}
