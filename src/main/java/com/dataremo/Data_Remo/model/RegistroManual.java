package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.service.SistemaRemada;

public class RegistroManual extends RegistroRemada {

    private SistemaRemada sistema;

    public RegistroManual(SistemaRemada sistema) {
        this.sistema = sistema;
    }

    @Override
    protected boolean validar(Remada remada) {
        try {
            SessaoTreino sessao = SessaoFactory.criar(remada.getTipo());
            boolean valido = sessao.validar(remada.getDuracao(), remada.getIntensidade());
            if (!valido)
                System.out.println("[VALIDAÇÃO] Fora dos parâmetros: " + sessao.descrever());
            return valido;
        } catch (IllegalArgumentException e) {
            System.out.println("[VALIDAÇÃO] " + e.getMessage());
            return false;
        }
    }

    @Override
    protected void processar(Remada remada, Atleta atleta) {
        double pontos = remada.calcularPontos();

        // Atualiza estatísticas do atleta
        atleta.incrementarTreinos();
        atleta.adicionarTempo(remada.getDuracao());
        atleta.adicionarPontos(pontos);

        // Usa getPontosTotais(), não mais getPontos()
        double totalPontos = atleta.getPontosTotais() != null ? atleta.getPontosTotais() : 0.0;

        System.out.printf("[PONTOS] +%.1f pts para %s (%.0fmin / %d) | Total: %.1f%n",
                pontos, atleta.getNome(),
                (double) remada.getDuracao(),
                remada.getIntensidade(),
                totalPontos);
    }

    @Override
    protected void notificarConclusao(Remada remada, Atleta atleta) {
        sistema.notificar("Treino registrado!", remada, atleta);
    }
}