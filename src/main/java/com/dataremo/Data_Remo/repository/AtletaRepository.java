package com.dataremo.Data_Remo.repository;

import com.dataremo.Data_Remo.model.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {

    Atleta findByNome(String nome);

    Atleta findByNomeAndEmail(String nome, String email);

    // usado pelo ranking geral
    List<Atleta> findAllByOrderByPontosTotaisDesc();
}