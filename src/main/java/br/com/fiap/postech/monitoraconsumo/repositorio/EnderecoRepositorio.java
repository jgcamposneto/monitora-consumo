package br.com.fiap.postech.monitoraconsumo.repositorio;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Repository
public class EnderecoRepositorio {

    static private Set<Endereco> enderecos;

    static {
        enderecos = new HashSet<>();
        Endereco endereco = new Endereco();
        endereco.setRua("Rua 1").setNumero(1).setBairro("Bairro1").setCidade("Cidade1").setEstado("Estado1");
        save(endereco);
    }

    public EnderecoRepositorio() {
        enderecos = new HashSet<>();
    }

    public static Endereco save(Endereco endereco){
        endereco.setId(enderecos.size() + 1L);
        enderecos.add(endereco);
        return endereco;
    }

    public Set<Endereco> findAll() {
        return enderecos;
    }

    public Optional<Endereco> findById(Long id) {
        return enderecos.stream().filter(endereco -> endereco.getId().equals(id)).findFirst();
    }
}
