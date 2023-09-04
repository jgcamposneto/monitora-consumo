package br.com.fiap.postech.monitoraconsumo.service;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.form.EnderecoForm;
import br.com.fiap.postech.monitoraconsumo.form.PessoaForm;
import br.com.fiap.postech.monitoraconsumo.repository.IEnderecoRepository;
import br.com.fiap.postech.monitoraconsumo.service.exception.ControllerNotFoundException;
import br.com.fiap.postech.monitoraconsumo.service.exception.DatabaseException;
import br.com.fiap.postech.monitoraconsumo.service.exception.ServiceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EletrodomesticoService eletrodomesticoService;

    public Collection<Endereco> findAll() {
        var enderecos = enderecoRepository.findAll();

        return enderecos;
    }

    public Endereco findById(UUID id) {
        var endereco = enderecoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Endereco não encontrado"));
        return endereco;
    }

    public Endereco save(Endereco endereco) {
        var enderecoSaved = enderecoRepository.save(endereco);
        return enderecoSaved;
    }

    public Endereco update(UUID id, Endereco endereco) {
        try {
            Endereco findEndereco = enderecoRepository.getOne(id);
            findEndereco.setRua(endereco.getRua());
            findEndereco.setNumero(endereco.getNumero());
            findEndereco.setBairro(endereco.getBairro());
            findEndereco.setCidade(endereco.getCidade());
            findEndereco.setEstado(endereco.getEstado());
            findEndereco = enderecoRepository.save(findEndereco);

            return findEndereco;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereço não encontrado, id:" + id);
        }
    }

    public void delete(UUID id) {
        try {
            enderecoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entity not found with ID: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base");
        }
    }

    public Endereco adicionarPessoa(UUID idEndereco, UUID idPessoa) {
        Pessoa pessoa = pessoaService.findById(idPessoa);
        Endereco endereco = findById(idEndereco);
        if(endereco.getPessoas().contains(pessoa))
            throw new ServiceException("Pessoa já cadastrada para o endereço!");
        endereco.addPessoa(pessoa);
        pessoaService.save(pessoa);
        return endereco;
    }

    public Endereco adicionarEletrodomestico(UUID idEndereco, UUID idEletrodomestico) {
        Eletrodomestico eletrodomestico = eletrodomesticoService.findById(idEletrodomestico);
        Endereco endereco = findById(idEndereco);
        if(endereco.getPessoas().contains(eletrodomestico))
            throw new ServiceException("Eletroméstico já cadastrado para o endereço!");
        endereco.addEletrodomestico(eletrodomestico);
        eletrodomesticoService.save(eletrodomestico);
        return endereco;
    }

    public ResponseEntity<List<EnderecoForm>> getEnderecos(String rua, String bairro, String cidade) {
        List<Endereco> findEnderecos = enderecoRepository.getEnderecos(rua, bairro, cidade);
        if (findEnderecos == null) throw new ControllerNotFoundException("Nenhum endereco encontrado");
        List<EnderecoForm> enderecosForm = findEnderecos.stream().map(EnderecoForm::new).collect(Collectors.toList());
        return ResponseEntity.ok(enderecosForm);
    }
}
