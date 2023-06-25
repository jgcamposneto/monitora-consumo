package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.form.EnderecoForm;
import br.com.fiap.postech.monitoraconsumo.repositorio.EnderecoRepositorio;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Collection<Endereco>> findAll() {
        var enderecos = enderecoRepositorio.findAll();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Endereco>> findById(@PathVariable Long id) {
        var endereco = enderecoRepositorio.findById(id);
        return ResponseEntity.ok(endereco);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody EnderecoForm enderecoForm) {
        var violacoesToMap = validar(enderecoForm);
        if(!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var endereco = enderecoForm.toEndereco();
        enderecoRepositorio.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoesToMap;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        enderecoRepositorio.delete(id);
        return ResponseEntity.ok("Delete com sucesso");
    }

}
