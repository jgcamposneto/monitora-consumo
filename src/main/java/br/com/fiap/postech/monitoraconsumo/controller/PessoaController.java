package br.com.fiap.postech.monitoraconsumo.controller;

import br.com.fiap.postech.monitoraconsumo.dominio.Parentesco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.dominio.Sexo;
import br.com.fiap.postech.monitoraconsumo.form.PessoaForm;
import br.com.fiap.postech.monitoraconsumo.service.PessoaService;
import br.com.fiap.postech.monitoraconsumo.service.UsuarioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private Validator validator;

    @GetMapping
    public ResponseEntity<Collection<Pessoa>> findAll() {
        var pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable UUID id) {
        var pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PessoaForm>> findPessoas(@RequestParam(name = "nome",required=false) String nome,
                                                      @RequestParam(name = "parentesco",required=false) Parentesco parentesco,
                                                      @RequestParam(name = "sexo",required=false) Sexo sexo) {

        return pessoaService.getPessoas(nome, parentesco, sexo);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PessoaForm pessoaForm) {
        var violacoesToMap = validar(pessoaForm);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var pessoa = pessoaForm.toPessoa();
        var usuario = usuarioService.findById(pessoa.getUsuario().getId());
        pessoa.setUsuario(usuario);
        var pessoaSaved = pessoaService.save(pessoa);
        pessoaForm.setId(pessoaSaved.getId());
        var uri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((pessoaSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(pessoaForm);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody PessoaForm pessoaForm) {
        var violacoesToMap = validar(pessoaForm);
        if (!violacoesToMap.isEmpty())
            return ResponseEntity.badRequest().body(violacoesToMap);
        var pessoa = pessoaForm.toPessoa();
        var pessoaAdicionada = pessoaService.update(id, pessoa);
        pessoaForm.setId(pessoaAdicionada.getId());
        return ResponseEntity.ok(pessoaForm);
    }
    @GetMapping("/{id}/verificaParentesco")
    public ResponseEntity<String> verificaParentesco(@PathVariable UUID id, @RequestParam Parentesco parentesco) {
        boolean possuiParentesco = pessoaService.verificaParentesco(id, parentesco);
        if (possuiParentesco) {
            return ResponseEntity.ok("A pessoa possui o parentesco: " + parentesco);
        } else {
            return ResponseEntity.ok("A pessoa não possui o parentesco: " + parentesco);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private <T> Map<Path, String> validar(T form) {
        var violacoes = validator.validate(form);
        var violacoesToMap = violacoes.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return violacoesToMap;
    }

}
