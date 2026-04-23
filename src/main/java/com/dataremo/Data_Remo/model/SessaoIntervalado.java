package com.dataremo.Data_Remo.model;

public class SessaoIntervalado extends SessaoTreino {
    public SessaoIntervalado() { this.tipo = "Intervalado"; }
    @Override public String descrever() {
        return "Tiros curtos de alta intensidade (7-10, mín 20min)";
    }
    @Override public boolean validar(int duracao, int intensidade) {
        return duracao >= 20 && intensidade >= 7;
    }
}