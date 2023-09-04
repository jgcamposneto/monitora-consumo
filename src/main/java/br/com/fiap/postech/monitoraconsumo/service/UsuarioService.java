package br.com.fiap.postech.monitoraconsumo.service;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.dominio.Usuario;
import br.com.fiap.postech.monitoraconsumo.repository.IEnderecoRepository;
import br.com.fiap.postech.monitoraconsumo.repository.IUsuarioRepository;
import br.com.fiap.postech.monitoraconsumo.service.exception.ControllerNotFoundException;
import br.com.fiap.postech.monitoraconsumo.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    public Collection<Usuario> findAll() {
        var usuarios = repository.findAll();

        return usuarios;
    }

    public Usuario findById(UUID id) {
        var usuario =
                repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado"));
        return usuario;
    }

    public Usuario save(Usuario usuario) {
        var usuarioSaved = repository.save(usuario);
        return usuarioSaved;
    }

    public Usuario update(UUID id, Usuario usuario) {
        try {
            Usuario findUsuario = repository.getOne(id);
            findUsuario.setNome(usuario.getNome());
            findUsuario = repository.save(findUsuario);

            return findUsuario;
        } catch (EntityNotFoundException e) {
            throw  new ControllerNotFoundException("Usário não encontrado, id:" + id);
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
}
