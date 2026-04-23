package com.dataremo.Data_Remo.model;

public abstract class SessaoTreino {
    protected String tipo;
    public abstract String descrever();
    public abstract boolean validar(int duracao, int intensidade);
}
