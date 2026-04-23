package com.dataremo.Data_Remo.service;

import com.dataremo.Data_Remo.model.*;

public class ModuloAnalise implements Observador {

    @Override
    public void atualizar(String mensagem, Remada remada, Atleta atleta) {
        // Cria o relatório adequado ao plano do atleta
        RelatorioAnalise relatorio = RelatorioFactory.criar(atleta.getPlano());
        relatorio.gerar(remada, atleta);

        // Aviso de upgrade para usuários Free
        if (atleta.getPlano() == Plano.FREE) {
            System.out.println("\n┌─ 🔒 RECURSOS PLUS ─┐");
            System.out.println("  Relatório semanal, mensal e ranking");
            System.out.println("  estão disponíveis no plano Plus.");
            System.out.println("  Faça upgrade para desbloquear!");
            System.out.println("└────────────────────┘");
        }
    }
}