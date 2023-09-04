package br.com.fiap.postech.monitoraconsumo.form;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Collections;
import java.util.UUID;

@Data
public class UsuarioForm {

    @JsonProperty
    private UUID id;

    @JsonProperty
    @NotBlank(message = "Nome é um campo obrigatório e não pode estar em branco.")
    private String nome;

    public Usuario toUsuario() {
        return new Usuario().setNome(nome).setEnderecos(Collections.<Endereco>emptyList() );
    }

}
