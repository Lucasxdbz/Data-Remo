package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.repository.BancoDados;

import java.util.List;

public class RelatorioMensalDecorator extends RelatorioDecorator {

    public RelatorioMensalDecorator(RelatorioAnalise relatorio) {
        super(relatorio);
    }

    @Override
    public void gerar(Remada remada, Atleta atleta) {
        super.gerar(remada, atleta);
        gerarMensal(remada, atleta);
    }

    private void gerarMensal(Remada remada, Atleta atleta) {
        List<Remada> mes = BancoDados.getInstance()
                .getRemadasPorMes(atleta.getNome(), remada.getMes());

        System.out.println("\n┌─ RELATÓRIO MENSAL — " + remada.getMes() + " ─┐");

        if (!mes.isEmpty()) {
            int    totalDuracao = mes.stream().mapToInt(Remada::getDuracao).sum();
            double totalPontos  = mes.stream().mapToDouble(Remada::calcularPontos).sum();
            double mediaInt     = mes.stream().mapToInt(Remada::getIntensidade).average().orElse(0);
            double mediaPts     = mes.stream().mapToDouble(Remada::calcularPontos).average().orElse(0);
            int    totalTreinos = mes.size();

            System.out.println("  Treinos no mês           : " + totalTreinos);
            System.out.println("  Tempo total              : " + totalDuracao + " min"
                    + " (" + totalDuracao / 60 + "h " + totalDuracao % 60 + "min)");
            System.out.printf ("  Pontos no mês            : %.1f pts%n", totalPontos);
            System.out.printf ("  Intensidade média        : %.1f/10%n",  mediaInt);
            System.out.printf ("  Pontos médios/treino     : %.1f pts%n", mediaPts);

            // Melhor treino do mês
            Remada melhor = mes.stream()
                    .max((a, b) -> Double.compare(a.calcularPontos(), b.calcularPontos()))
                    .orElse(null);
            if (melhor != null)
                System.out.printf("  Melhor treino do mês     : %s — %.1f pts (%s)%n",
                        melhor.getData(), melhor.calcularPontos(), melhor.getTipo());

            // Gráfico mensal
            System.out.println("\n┌─ GRÁFICO MENSAL ─┐");
            for (Remada r : mes) {
                String barra    = gerarBarra(r.calcularPontos());
                String marcador = r.getData().equals(remada.getData()) ? " ← HOJE" : "";
                System.out.printf("  %-12s | %-22s %.1f pts%s%n",
                        r.getData(), barra, r.calcularPontos(), marcador);
            }

            System.out.println("\n┌─ INSIGHT MENSAL ─┐");
            if (totalTreinos >= 12)
                System.out.println("  🏆 Mês excepcional! Mais de 12 treinos registrados.");
            else if (totalTreinos >= 8)
                System.out.println("  🔥 Ótimo mês! Consistência acima da média.");
            else if (totalTreinos >= 4)
                System.out.println("  ✔  Mês razoável. Tente chegar a 8 treinos.");
            else
                System.out.println("  💡 Mês fraco. Estabeleça uma rotina semanal.");
        }
    }

    private String gerarBarra(double pontos) {
        int tamanho = Math.min((int)(pontos / 1.5), 22);
        return "█".repeat(Math.max(tamanho, 1));
    }
}