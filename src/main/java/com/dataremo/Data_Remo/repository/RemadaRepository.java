package com.dataremo.Data_Remo.repository;

import com.dataremo.Data_Remo.model.Remada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemadaRepository extends JpaRepository<Remada, Long> {
    List<Remada> findByAtleta(String atleta);
    List<Remada> findByAtletaAndSemana(String atleta, String semana);
    List<Remada> findByAtletaAndMes(String atleta, String mes);
}