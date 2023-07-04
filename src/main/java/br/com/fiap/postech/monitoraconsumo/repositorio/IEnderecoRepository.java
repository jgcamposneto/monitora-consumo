package br.com.fiap.postech.monitoraconsumo.repositorio;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, UUID> {

}
