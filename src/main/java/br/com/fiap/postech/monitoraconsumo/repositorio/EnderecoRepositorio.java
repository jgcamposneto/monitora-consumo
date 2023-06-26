package br.com.fiap.postech.monitoraconsumo.repositorio;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EnderecoRepositorio {

    static private Set<Endereco> enderecos;

    public EnderecoRepositorio() {
        enderecos = new HashSet<>();
    }

    public Endereco save(Endereco endereco){
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

    public void delete(Long id) {
        enderecos.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Endereco> update(Endereco enderecoAtualizacao) {
        Optional<Endereco> enderecoASerBuscado = this.findById(enderecoAtualizacao.getId());

        if(enderecoASerBuscado.isPresent()) {
            Endereco endereco = enderecoASerBuscado.get();
            endereco.setRua(enderecoAtualizacao.getRua());
            endereco.setNumero(enderecoAtualizacao.getNumero());
            endereco.setBairro(enderecoAtualizacao.getBairro());
            endereco.setCidade(enderecoAtualizacao.getCidade());
            endereco.setEstado(enderecoAtualizacao.getEstado());

            return Optional.of(endereco);
        }

        return Optional.empty();
    }
}
