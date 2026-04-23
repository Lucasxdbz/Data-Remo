package com.dataremo.Data_Remo.service;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.model.Observador;
import com.dataremo.Data_Remo.model.Remada;
import com.dataremo.Data_Remo.model.Sujeito;

import java.util.ArrayList;
import java.util.List;

public class SistemaRemada implements Sujeito {
    private List<Observador> observadores = new ArrayList<>();

    @Override
    public void adicionar(Observador o) { observadores.add(o); }

    @Override
    public void remover(Observador o) { observadores.remove(o); }

    @Override
    public void notificar(String mensagem, Remada remada, Atleta atleta) {
        for (Observador o : observadores)
            o.atualizar(mensagem, remada, atleta);
    }
}
