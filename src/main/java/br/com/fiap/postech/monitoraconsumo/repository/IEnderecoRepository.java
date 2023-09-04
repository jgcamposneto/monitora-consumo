package br.com.fiap.postech.monitoraconsumo.repository;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, UUID> {
    @Query("""
        SELECT e
        FROM Endereco e
        WHERE e.rua = :rua OR e.bairro = :bairro OR e.cidade = :cidade
        """)
    Endereco getEndereco(String rua, String bairro, String cidade);
}
