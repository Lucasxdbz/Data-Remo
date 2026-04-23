package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.repository.BancoDados;

import java.util.List;

public class RelatorioDiario implements RelatorioAnalise {

    @Override
    public void gerar(Remada remada, Atleta atleta) {

        // historico de remadas usando o nome do atleta
        List<Remada> historico = BancoDados.getInstance()
                .getRemadasPorAtleta(atleta.getNome());

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║         RELATÓRIO DIÁRIO                     ║");
        System.out.printf ("║  Atleta : %-35s║%n", atleta.getNome());

        // se plano hoje é String, usamos direto
        System.out.printf ("║  Plano  : %-35s║%n", atleta.getPlano());

        // nível vem do enum Nivel
        System.out.printf ("║  Nível  : %-35s║%n", atleta.getNivel().getNome());
        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.println("\n┌─ TREINO DO DIA — " + remada.getData() + " ─┐");
        System.out.println("  Tipo        : " + remada.getTipo());
        System.out.println("  Local       : " + remada.getLocal());
        System.out.println("  Duração     : " + remada.getDuracao() + " min");
        System.out.println("  Intensidade : " + remada.getIntensidade() + "/10");
        System.out.printf ("  Pontos      : %.0fmin ÷ %d = +%.1f pts%n",
                (double) remada.getDuracao(),
                remada.getIntensidade(),
                remada.calcularPontos());
        System.out.println("  Observações : " + remada.getComentarios());

        // Gráfico de pontos por treino — histórico completo
        System.out.println("\n┌─ GRÁFICO — PONTOS POR TREINO ─┐");
        for (Remada r : historico) {
            String barra    = gerarBarra(r.calcularPontos());
            String marcador = r.getData().equals(remada.getData()) ? " ← HOJE" : "";
            System.out.printf("  %-12s | %-22s %.1f pts%s%n",
                    r.getData(), barra, r.calcularPontos(), marcador);
        }

        // Variação do dia vs média histórica
        if (historico.size() > 1) {
            double mediaPontos = historico.stream()
                    .mapToDouble(Remada::calcularPontos).average().orElse(0);
            double mediaInt = historico.stream()
                    .mapToInt(Remada::getIntensidade).average().orElse(0);
            double mediaDur = historico.stream()
                    .mapToInt(Remada::getDuracao).average().orElse(0);
            double variacaoDia = ((remada.calcularPontos() - mediaPontos)
                    / mediaPontos) * 100;

            System.out.println("\n┌─ ANÁLISE DO DIA ─┐");
            System.out.printf("  Média pts/treino         : %.1f pts%n",  mediaPontos);
            System.out.printf("  Pontos hoje              : %.1f pts%n",  remada.calcularPontos());
            System.out.printf("  Média intensidade        : %.1f/10%n",   mediaInt);
            System.out.printf("  Média duração            : %.0f min%n",  mediaDur);

            if (variacaoDia > 0)
                System.out.printf("  Variação                 : ↑ +%.1f%% acima da média%n",
                        variacaoDia);
            else if (variacaoDia < 0)
                System.out.printf("  Variação                 : ↓ -%.1f%% abaixo da média%n",
                        Math.abs(variacaoDia));
            else
                System.out.println("  Variação                 : → exatamente na média");

            System.out.println("\n┌─ INSIGHT DO DIA ─┐");
            if (variacaoDia > 40)
                System.out.println("  ⚠  Sessão muito leve ou longa demais.");
            else if (variacaoDia < -40)
                System.out.println("  💡 Sessão curta ou intensa demais. Equilibre duração e esforço.");
            else
                System.out.println("  ✔  Treino bem calibrado. Continue assim!");
        }

        // Progresso de nível — disponível para todos
        exibirProgressoNivel(atleta);
    }

    // Barra visual proporcional aos pontos
    protected String gerarBarra(double pontos) {
        int tamanho = Math.min((int) (pontos / 1.5), 22);
        return "█".repeat(Math.max(tamanho, 1));
    }

    // Progresso para o próximo nível — usando os novos campos de Atleta
    protected void exibirProgressoNivel(Atleta atleta) {
        Nivel[] niveis = Nivel.values();
        int indice = atleta.getNivel().ordinal();

        if (indice < niveis.length - 1) {
            Nivel proximo = niveis[indice + 1];

            double pontosAtuais = atleta.getPontosTotais() != null ? atleta.getPontosTotais() : 0.0;
            int treinosAtuais = atleta.getTotalTreinos() != null ? atleta.getTotalTreinos() : 0;
            int tempoAtual = atleta.getTempoTotalMinutos() != null ? atleta.getTempoTotalMinutos() : 0;

            double ptRestantes = Math.max(0, proximo.getPontosParaSubir() - pontosAtuais);
            int treinosRestantes = Math.max(0, proximo.getTreinosMinimos() - treinosAtuais);
            int tempoRestante = Math.max(0, proximo.getTempoMinimoAcum() - tempoAtual);

            System.out.println("\n┌─ PRÓXIMO NÍVEL: " + proximo.getNome() + " ─┐");
            System.out.printf ("  Pontos restantes         : %.1f pts%n", ptRestantes);
            System.out.println("  Treinos restantes        : " + treinosRestantes);
            System.out.println("  Tempo restante           : " + tempoRestante + " min");

            int prog = proximo.getPontosParaSubir() > 0
                    ? (int) ((pontosAtuais * 20.0) / proximo.getPontosParaSubir())
                    : 0;
            prog = Math.min(Math.max(prog, 0), 20);

            String barraP = "█".repeat(prog) + "░".repeat(20 - prog);
            System.out.printf("  Progresso                : [%s] %.1f/%d%n",
                    barraP, pontosAtuais, proximo.getPontosParaSubir());
        } else {
            System.out.println("\n  👑 NÍVEL MÁXIMO — " + atleta.getNivel().getNome());
        }
    }
}