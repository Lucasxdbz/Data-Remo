package com.dataremo.Data_Remo.model;

public abstract class RelatorioDecorator implements RelatorioAnalise {

    // Componente que está sendo decorado
    protected RelatorioAnalise relatorio;

    public RelatorioDecorator(RelatorioAnalise relatorio) {
        this.relatorio = relatorio;
    }

    // Delega ao componente base e adiciona comportamento
    @Override
    public void gerar(Remada remada, Atleta atleta) {
        relatorio.gerar(remada, atleta);
    }
}