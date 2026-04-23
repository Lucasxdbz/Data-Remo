package com.dataremo.Data_Remo.console;

import com.dataremo.Data_Remo.model.*;
import com.dataremo.Data_Remo.repository.BancoDados;
import com.dataremo.Data_Remo.service.ModuloAnalise;
import com.dataremo.Data_Remo.service.ModuloBadges;
import com.dataremo.Data_Remo.service.SistemaRemada;

public class Main {
    public static void main(String[] args) {

        SistemaRemada sistema = new SistemaRemada();
        sistema.adicionar(new ModuloAnalise());
        sistema.adicionar(new ModuloBadges());

        RegistroRemada registro = new RegistroManual(sistema);

        // Atleta FREE — acessa apenas relatório diário
        Atleta gabriel = new Atleta("Gabriel", 22, 1.80,
                "SUP", "Condicionamento", Plano.FREE);

        // Atleta PLUS — acessa tudo
        Atleta lucas = new Atleta("Lucas", 19, 1.75,
                "Surf", "Performance", Plano.PLUS);

        Atleta ana = new Atleta("Ana", 25, 1.65,
                "Caiaque", "Evolução técnica", Plano.PLUS);

        BancoDados.getInstance().salvarAtleta(gabriel);
        BancoDados.getInstance().salvarAtleta(lucas);
        BancoDados.getInstance().salvarAtleta(ana);

        // Pontuação inicial de Ana para o ranking ter contexto
        ana.incrementarTreinos();
        ana.adicionarTempo(45);
        ana.adicionarPontos(9.0);

        // ══════════════════════════════════════════════════
        // CASO 1: Gabriel (FREE) registra um treino
        // ══════════════════════════════════════════════════
        System.out.println("\n════════ CASO 1: Gabriel — Plano FREE ════════\n");

        Remada r1 = new Remada("Gabriel",
                "22/04/2026", "Semana 17", "Abril/2026",
                "Ipanema", 60, "endurance", 5, "Mar calmo, bom treino");
        registro.executar(r1, gabriel);

        // ══════════════════════════════════════════════════
        // CASO 2: Lucas (PLUS) registra 3 treinos no mês
        // ══════════════════════════════════════════════════
        System.out.println("\n════════ CASO 2: Lucas — Plano PLUS ════════\n");

        Object[][] treinosLucas = {
                {"20/04/2026","Semana 17","Abril/2026","Barra",   45,"endurance",  5,"Bom ritmo"},
                {"21/04/2026","Semana 17","Abril/2026","Ipanema", 30,"tecnico",    3,"Técnica de remada"},
                {"22/04/2026","Semana 17","Abril/2026","Recreio", 50,"endurance",  4,"Longa distância"}
        };

        for (Object[] t : treinosLucas) {
            Remada r = new Remada("Lucas",
                    (String)t[0], (String)t[1], (String)t[2], (String)t[3],
                    (int)t[4], (String)t[5], (int)t[6], (String)t[7]);
            registro.executar(r, lucas);
        }
    }
}