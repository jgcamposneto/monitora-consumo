package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.repositorio.EnderecoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

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
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
        enderecoRepositorio.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }

}
