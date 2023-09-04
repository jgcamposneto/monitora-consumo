package br.com.fiap.postech.monitoraconsumo.service;

import br.com.fiap.postech.monitoraconsumo.dominio.Parentesco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.dominio.Sexo;
import br.com.fiap.postech.monitoraconsumo.form.PessoaForm;
import br.com.fiap.postech.monitoraconsumo.repository.IPessoaRepository;
import br.com.fiap.postech.monitoraconsumo.service.exception.ControllerNotFoundException;
import br.com.fiap.postech.monitoraconsumo.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private IPessoaRepository repository;

    public Collection<Pessoa> findAll() {
        var pessoas = repository.findAll();
        return pessoas;
    }

    public Pessoa findById(UUID id) {
        var pessoa =
                repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pessoa não encontrada"));
        return pessoa;
    }

    public Pessoa save(Pessoa pessoa) {
        var pessoaSaved = repository.save(pessoa);
        return pessoaSaved;
    }

    public Pessoa update(UUID id, Pessoa pessoa) {
        try {
            Pessoa findPessoa = repository.getOne(id);
            findPessoa.setNome(pessoa.getNome());
            findPessoa.setSexo(pessoa.getSexo());
            findPessoa.setParentesco(pessoa.getParentesco());
            findPessoa.setDataDeNascimento(pessoa.getDataDeNascimento());
            findPessoa = repository.save(findPessoa);

            return findPessoa;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pessoa não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }

    }

    public ResponseEntity<PessoaForm> getPessoa(String nome, Parentesco parentesco, Sexo sexo) {
        Pessoa findPessoa = repository.getPessoa(nome, parentesco, sexo);
        if (findPessoa == null) throw new ControllerNotFoundException("Pessoa não encontrada");

        PessoaForm pessoaForm = new PessoaForm(findPessoa);
        return ResponseEntity.ok(pessoaForm);
    }
}
