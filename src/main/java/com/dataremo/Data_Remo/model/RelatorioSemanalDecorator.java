package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.repository.BancoDados;

import java.util.List;

public class RelatorioSemanalDecorator extends RelatorioDecorator {

    public RelatorioSemanalDecorator(RelatorioAnalise relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Remada remada, Atleta atleta) {
        // Primeiro gera o relatório base
        super.gerar(remada, atleta);
        // Depois adiciona o semanal
        gerarSemanal(remada, atleta);
    }

    private void gerarSemanal(Remada remada, Atleta atleta) {
        List<Remada> semanaAtual = BancoDados.getInstance()
                .getRemadasPorSemana(atleta.getNome(),
                        remada.getSemana());

        System.out.println("\n┌─ RELATÓRIO SEMANAL — " + remada.getSemana() + " ─┐");

        if (!semanaAtual.isEmpty()) {
            int    totalDuracao   = semanaAtual.stream().mapToInt(Remada::getDuracao).sum();
            double totalPontos    = semanaAtual.stream().mapToDouble(Remada::calcularPontos).sum();
            double mediaInt       = semanaAtual.stream().mapToInt(Remada::getIntensidade).average().orElse(0);
            double mediaDur       = semanaAtual.stream().mapToInt(Remada::getDuracao).average().orElse(0);
            double mediaPts       = semanaAtual.stream().mapToDouble(Remada::calcularPontos).average().orElse(0);

            System.out.println("  Treinos realizados       : " + semanaAtual.size());
            System.out.println("  Tempo total              : " + totalDuracao + " min");
            System.out.printf ("  Pontos na semana         : %.1f pts%n",  totalPontos);
            System.out.printf ("  Intensidade média        : %.1f/10%n",   mediaInt);
            System.out.printf ("  Duração média/treino     : %.0f min%n",  mediaDur);
            System.out.printf ("  Pontos médios/treino     : %.1f pts%n",  mediaPts);

            // Gráfico semanal
            System.out.println("\n┌─ GRÁFICO SEMANAL ─┐");
            for (Remada r : semanaAtual) {
                String barra    = gerarBarra(r.calcularPontos());
                String marcador = r.getData().equals(remada.getData()) ? " ← HOJE" : "";
                System.out.printf("  %-12s | %-22s %.1f pts%s%n",
                        r.getData(), barra, r.calcularPontos(), marcador);
            }

            System.out.println("\n┌─ INSIGHT SEMANAL ─┐");
            if (semanaAtual.size() >= 4 && totalPontos >= 40)
                System.out.println("  🔥 Semana excelente! Alto volume e consistência.");
            else if (semanaAtual.size() >= 3)
                System.out.println("  ✔  Boa semana. Tente manter a frequência!");
            else
                System.out.println("  💡 Poucos treinos. Tente aumentar a frequência.");
        }
    }

    private String gerarBarra(double pontos) {
        int tamanho = Math.min((int)(pontos / 1.5), 22);
        return "█".repeat(Math.max(tamanho, 1));
    }
}
