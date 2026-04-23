package com.dataremo.Data_Remo.model;

public enum Nivel {
    
    INICIANTE_1    ("Iniciante 1",     0,     0,    0),
    INICIANTE_2    ("Iniciante 2",     50,    5,    150),
    INICIANTE_3    ("Iniciante 3",     120,   10,   350),
    INTERMEDIARIO_1("Intermediário 1", 250,   20,   700),
    INTERMEDIARIO_2("Intermediário 2", 450,   35,   1300),
    INTERMEDIARIO_3("Intermediário 3", 700,   55,   2100),
    AVANCADO_1     ("Avançado 1",      1000,  80,   3000),
    AVANCADO_2     ("Avançado 2",      1400,  120,  4500),
    AVANCADO_3     ("Avançado 3",      2000,  180,  6500);

    private final String nome;
    private final int pontosParaSubir;
    private final int treinosMinimos;
    private final int tempoMinimoAcum;

    Nivel(String nome, int pontosParaSubir, int treinosMinimos, int tempoMinimoAcum) {
        this.nome = nome;
        this.pontosParaSubir = pontosParaSubir;
        this.treinosMinimos = treinosMinimos;
        this.tempoMinimoAcum = tempoMinimoAcum;
    }

    public String getNome()         { return nome; }
    public int getPontosParaSubir() { return pontosParaSubir; }
    public int getTreinosMinimos()  { return treinosMinimos; }
    public int getTempoMinimoAcum() { return tempoMinimoAcum; }

}

