package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import br.com.fiap.postech.monitoraconsumo.form.EletrodomesticoForm;
import br.com.fiap.postech.monitoraconsumo.form.UsuarioForm;
import br.com.fiap.postech.monitoraconsumo.service.UsuarioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Validator validator;



    @GetMapping
    public ResponseEntity<Collection<Usuario>> findAll() {
        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UsuarioForm usuarioForm) {
        var violacoesToMap = validar(usuarioForm);
        if(!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var usuario = usuarioForm.toUsuario();
        var usuarioSaved = usuarioService.save(usuario);
        usuarioForm.setId(usuarioSaved.getId());
        var uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((usuarioSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(usuarioForm);
    }


    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody UsuarioForm usuarioForm) {
        var violacoesToMap = validar(usuarioForm);
        if(!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var usuario = usuarioForm.toUsuario();
        var usuarioAdicionado = usuarioService.update(id, usuario);
        usuarioForm.setId(usuarioAdicionado.getId());
        return ResponseEntity.ok(usuarioForm);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return violacoesToMap;
    }
}
