package com.dataremo.Data_Remo.model;

import com.dataremo.Data_Remo.repository.BancoDados;

public abstract class RegistroRemada {

    public final void executar(Remada remada, Atleta atleta) {
        if (!validar(remada)) {
            System.out.println("[SISTEMA] Remada inválida — registro cancelado.");
            return;
        }
        processar(remada, atleta);
        salvar(remada);
        notificarConclusao(remada, atleta);
    }

    private void salvar(Remada remada) {
        BancoDados.getInstance().salvarRemada(remada);
        System.out.println("[BANCO] Remada salva com sucesso.");
    }

    protected abstract boolean validar(Remada remada);
    protected abstract void processar(Remada remada, Atleta atleta);
    protected abstract void notificarConclusao(Remada remada, Atleta atleta);
}