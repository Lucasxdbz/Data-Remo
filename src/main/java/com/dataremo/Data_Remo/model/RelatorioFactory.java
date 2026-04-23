package com.dataremo.Data_Remo.model;

public class RelatorioFactory {

    public static RelatorioAnalise criar(Plano plano) {
        // Base: relatório diário — sempre disponível
        RelatorioAnalise relatorio = new RelatorioDiario();

        if (plano == Plano.PLUS) {
            // PLUS: empilha semanal, mensal e ranking em cima do diário
            relatorio = new RelatorioSemanalDecorator(relatorio);
            relatorio = new RelatorioMensalDecorator(relatorio);
            relatorio = new RankingDecorator(relatorio);
        }

        return relatorio;
    }
}