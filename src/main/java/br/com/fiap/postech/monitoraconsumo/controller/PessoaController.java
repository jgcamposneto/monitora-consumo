package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.form.PessoaForm;
import br.com.fiap.postech.monitoraconsumo.repositorio.PessoaRepositorio;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    @Autowired
    private Validator validator;

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesToMap;
    }

    @GetMapping
    public ResponseEntity<Collection<Pessoa>> findAll() {
        var pessoas = pessoaRepositorio.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id) {
        var pessoa = pessoaRepositorio.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PessoaForm pessoaForm) {
        var violacoesToMap = validar(pessoaForm);
        if(!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var pessoa = pessoaForm.toPessoa();
        pessoaRepositorio.save(pessoa);
        pessoaForm.setId(pessoa.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaForm);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        pessoaRepositorio.delete(id);
        return ResponseEntity.ok("Delete com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PessoaForm pessoaForm) {
        var violacoesToMap = validar(pessoaForm);
        if(!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var pessoa =  pessoaForm.toPessoa();
        pessoa.setId(id);
        var pessoaAdicionada = pessoaRepositorio.update(pessoa);
        if (!pessoaAdicionada.isEmpty())
            pessoaForm.setId(id);
        return ResponseEntity.ok(pessoaForm);
    }
}
