package com.dataremo.Data_Remo.model;

public interface Sujeito {
    void adicionar(Observador o);
    void remover(Observador o);
    void notificar(String mensagem, Remada remada, Atleta atleta);
}
