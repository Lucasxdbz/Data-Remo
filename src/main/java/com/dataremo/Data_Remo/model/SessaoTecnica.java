package com.dataremo.Data_Remo.model;

public class SessaoTecnica extends SessaoTreino {
    public SessaoTecnica() { this.tipo = "Técnico"; }
    @Override public String descrever() {
        return "Foco em movimentos, baixa intensidade (1-5, mín 30min)";
    }
    @Override public boolean validar(int duracao, int intensidade) {
        return duracao >= 30 && intensidade <= 5;
    }
}