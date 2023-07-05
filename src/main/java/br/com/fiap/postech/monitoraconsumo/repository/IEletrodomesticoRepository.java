package br.com.fiap.postech.monitoraconsumo.repository;

import br.com.fiap.postech.monitoraconsumo.dominio.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEletrodomesticoRepository extends JpaRepository<Eletrodomestico, UUID> {}
