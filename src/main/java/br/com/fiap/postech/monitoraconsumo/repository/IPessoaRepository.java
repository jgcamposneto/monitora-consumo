package br.com.fiap.postech.monitoraconsumo.repository;

import br.com.fiap.postech.monitoraconsumo.dominio.Endereco;
import br.com.fiap.postech.monitoraconsumo.dominio.Parentesco;
import br.com.fiap.postech.monitoraconsumo.dominio.Pessoa;
import br.com.fiap.postech.monitoraconsumo.dominio.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, UUID> {
    @Query("""
        SELECT p
        FROM Pessoa p
        WHERE p.nome = :nome OR p.parentesco = :parentesco OR p.sexo = :sexo
        """)
    List<Pessoa> getPessoas(String nome, Parentesco parentesco, Sexo sexo);
}
