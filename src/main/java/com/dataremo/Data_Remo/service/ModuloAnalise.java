package com.dataremo.Data_Remo.service;

import com.dataremo.Data_Remo.model.*;

public class ModuloAnalise implements Observador {

    @Override
    public void atualizar(String mensagem, Remada remada, Atleta atleta) {
        // Cria o relatório adequado ao plano do atleta
        // Se RelatorioFactory.criar espera String, usamos getPlano():
        // RelatorioAnalise relatorio = RelatorioFactory.criar(atleta.getPlano());
        // Se espera Plano, usamos getPlanoEnum():
        Plano planoEnum = atleta.getPlanoEnum();
        RelatorioAnalise relatorio = RelatorioFactory.criar(planoEnum);

        relatorio.gerar(remada, atleta);

        // Aviso de upgrade para usuários Free
        if (planoEnum == Plano.FREE) {
            System.out.println("\n┌─ 🔒 RECURSOS PLUS ─┐");
            System.out.println("  Relatório semanal, mensal e ranking");
            System.out.println("  estão disponíveis no plano Plus.");
            System.out.println("  Faça upgrade para desbloquear!");
            System.out.println("└────────────────────┘");
        }
    }
}