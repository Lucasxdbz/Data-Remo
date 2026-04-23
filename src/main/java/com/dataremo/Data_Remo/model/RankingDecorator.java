package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.repository.BancoDados;

import java.util.List;

public class RankingDecorator extends RelatorioDecorator {

    public RankingDecorator(RelatorioAnalise relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Remada remada, Atleta atleta) {
        super.gerar(remada, atleta);
        gerarRanking(atleta);
    }

    private void gerarRanking(Atleta atleta) {

        System.out.println("\n┌─ RANKING GERAL ─┐");
        List<Atleta> rankingGeral = BancoDados.getInstance().getRankingGeral();
        int pos = 1;
        for (Atleta a : rankingGeral) {
            String marcador = a.getNome().equals(atleta.getNome()) ? " ← você" : "";

            double pontosA = a.getPontosTotais() != null ? a.getPontosTotais() : 0.0;

            System.out.printf("  %dº %-10s | %-15s | %.1f pts%s%n",
                    pos++, a.getNome(), a.getNivel().getNome(),
                    pontosA, marcador);
        }

        System.out.println("\n┌─ RANKING — " + atleta.getNivel().getNome() + " ─┐");
        List<Atleta> rankingNivel = BancoDados.getInstance()
                .getRankingPorNivel(atleta.getNivel());
        pos = 1;
        for (Atleta a : rankingNivel) {
            String marcador = a.getNome().equals(atleta.getNome()) ? " ← você" : "";

            double pontosA = a.getPontosTotais() != null ? a.getPontosTotais() : 0.0;

            System.out.printf("  %dº %-10s | %.1f pts%s%n",
                    pos++, a.getNome(), pontosA, marcador);
        }
    }
}