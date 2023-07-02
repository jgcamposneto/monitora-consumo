package br.com.fiap.postech.monitoraconsumo.repositorio;

import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaRepositorio {

    static private Set<Pessoa> pessoas;

    public PessoaRepositorio() { pessoas = new HashSet<>(); }

    public Pessoa save(Pessoa pessoa) {
        pessoa.setId(pessoas.size() + 1L);
        pessoas.add(pessoa);
        return pessoa;
    }

    public Set<Pessoa> findAll() { return pessoas; }

    public Optional<Pessoa> findById(Long id) {
        return pessoas.stream().filter(pessoa -> pessoa.getId().equals(id)).findFirst();
    }

    public void delete(Long id) { pessoas.removeIf(pessoa -> pessoa.getId().equals(id)); }

    public Optional<Pessoa> update(Pessoa pessoaAtualizacao) {
        Optional<Pessoa> pessoaASerBuscada = this.findById(pessoaAtualizacao.getId());

        if(pessoaASerBuscada.isPresent()) {
            Pessoa pessoa = pessoaASerBuscada.get();
            pessoa.setNome(pessoaAtualizacao.getNome());
            pessoa.setDataDeNascimento(pessoaAtualizacao.getDataDeNascimento());
            pessoa.setSexo(pessoaAtualizacao.getSexo());
            pessoa.setParentesco(pessoaAtualizacao.getParentesco());

            return Optional.of(pessoa);
        }

        return Optional.empty();
    }

}
