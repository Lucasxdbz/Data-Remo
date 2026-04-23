package com.dataremo.Data_Remo.model;

public class SessaoEndurance extends SessaoTreino {
    public SessaoEndurance() { this.tipo = "Endurance"; }
    @Override public String descrever() {
        return "Longa duração, intensidade moderada (4-6, mín 45min)";
    }
    @Override public boolean validar(int duracao, int intensidade) {
        return duracao >= 45 && intensidade >= 4 && intensidade <= 6;
    }
}