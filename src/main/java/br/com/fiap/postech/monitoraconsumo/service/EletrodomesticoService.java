package br.com.fiap.postech.monitoraconsumo.service;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.form.EletrodomesticoForm;
import br.com.fiap.postech.monitoraconsumo.form.PessoaForm;
import br.com.fiap.postech.monitoraconsumo.repository.IEletrodomesticoRepository;
import br.com.fiap.postech.monitoraconsumo.service.exception.ControllerNotFoundException;
import br.com.fiap.postech.monitoraconsumo.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EletrodomesticoService {

    @Autowired
    private IEletrodomesticoRepository repository;

    public Collection<Eletrodomestico> findAll() {
        var eletrodomesticos = repository.findAll();
        return eletrodomesticos;
    }

    public Eletrodomestico findById(UUID id) {
        var eletrodomestico =
                repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Eletrodoméstico não encontrado"));
        return eletrodomestico;
    }

    public Eletrodomestico save(Eletrodomestico eletrodomestico) {
        Eletrodomestico eletrodomesticoSaved = repository.save(eletrodomestico);
        return eletrodomesticoSaved;
    }

    public Eletrodomestico update(UUID id, Eletrodomestico eletrodomestico) {
        try {
            Eletrodomestico findEletrodomestico = repository.getOne(id);
            findEletrodomestico.setNome(eletrodomestico.getNome());
            findEletrodomestico.setModelo(eletrodomestico.getModelo());
            findEletrodomestico.setPotencia(eletrodomestico.getPotencia());
            findEletrodomestico = repository.save(findEletrodomestico);
            return findEletrodomestico;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Eletrodoméstico não encontrado, id" + id);
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

    public ResponseEntity<List<EletrodomesticoForm>> getEletrodomesticos(String nome, String modelo, BigDecimal potencia) {
        List<Eletrodomestico> findEletrodomesticos = repository.getEletrodomesticos(nome, modelo, potencia);
        if (findEletrodomesticos == null) throw new ControllerNotFoundException("Nenhum eletrodoméstico encontrado");
        List<EletrodomesticoForm> eletrodomesticosForm = findEletrodomesticos.stream().map(EletrodomesticoForm::new).collect(Collectors.toList());
        return ResponseEntity.ok(eletrodomesticosForm);
    }
}
