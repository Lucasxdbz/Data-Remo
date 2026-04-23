package com.dataremo.Data_Remo.model;

public enum Plano {
    FREE("Free", "Relatório diário apenas"),
    PLUS("Plus", "Relatório diário, semanal, mensal e ranking completo");

    private final String nome;
    private final String descricao;

    Plano(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }
}