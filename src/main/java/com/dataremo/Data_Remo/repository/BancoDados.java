package com.dataremo.Data_Remo.repository;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.model.Nivel;
import com.dataremo.Data_Remo.model.Remada;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {

    private static BancoDados instancia;
    private List<Atleta> atletas = new ArrayList<>();
    private List<Remada> remadas = new ArrayList<>();

    private BancoDados() {}

    public static BancoDados getInstance() {
        if (instancia == null) instancia = new BancoDados();
        return instancia;
    }

    public void salvarAtleta(Atleta a) { atletas.add(a); }
    public void salvarRemada(Remada r) { remadas.add(r); }

    // -------- REMADAS --------

    public List<Remada> getRemadasPorAtleta(String nome) {
        List<Remada> resultado = new ArrayList<>();
        for (Remada r : remadas) {
            // aqui r.getAtleta() provavelmente é um String (nome)
            if (nome.equals(r.getAtleta())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public List<Remada> getRemadasPorSemana(String nome, String semana) {
        List<Remada> resultado = new ArrayList<>();
        for (Remada r : remadas) {
            if (nome.equals(r.getAtleta()) && semana.equals(r.getSemana())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // Filtra remadas de um atleta por mês — usado no relatório mensal
    public List<Remada> getRemadasPorMes(String nome, String mes) {
        List<Remada> resultado = new ArrayList<>();
        for (Remada r : remadas) {
            if (nome.equals(r.getAtleta()) && mes.equals(r.getMes())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // -------- RANKING / ATLETAS --------

    public List<Atleta> getRankingGeral() {
        List<Atleta> ranking = new ArrayList<>(atletas);
        ranking.sort((a, b) -> {
            double pa = a.getPontosTotais() != null ? a.getPontosTotais() : 0.0;
            double pb = b.getPontosTotais() != null ? b.getPontosTotais() : 0.0;
            return Double.compare(pb, pa); // maior primeiro
        });
        return ranking;
    }

    public List<Atleta> getRankingPorNivel(Nivel nivel) {
        List<Atleta> ranking = new ArrayList<>();
        for (Atleta a : atletas) {
            if (a.getNivel() == nivel) {
                ranking.add(a);
            }
        }
        ranking.sort((a, b) -> {
            double pa = a.getPontosTotais() != null ? a.getPontosTotais() : 0.0;
            double pb = b.getPontosTotais() != null ? b.getPontosTotais() : 0.0;
            return Double.compare(pb, pa);
        });
        return ranking;
    }

    public List<Atleta> getAtletas() { return atletas; }

    public Atleta getAtletaPorNome(String nome) {
        for (Atleta a : atletas) {
            if (nome.equals(a.getNome())) return a;
        }
        return null;
    }
}