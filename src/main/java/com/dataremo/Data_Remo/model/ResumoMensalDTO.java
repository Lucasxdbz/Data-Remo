package com.dataremo.Data_Remo.model;

public class ResumoMensalDTO {

    private String atleta;
    private String mes;
    private int totalTreinos;
    private int minutosTotais;
    private double pontosTotais;
    private double pontosMediosPorTreino;

    public ResumoMensalDTO() {
    }

    public String getAtleta() {
        return atleta;
    }

    public void setAtleta(String atleta) {
        this.atleta = atleta;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getTotalTreinos() {
        return totalTreinos;
    }

    public void setTotalTreinos(int totalTreinos) {
        this.totalTreinos = totalTreinos;
    }

    public int getMinutosTotais() {
        return minutosTotais;
    }

    public void setMinutosTotais(int minutosTotais) {
        this.minutosTotais = minutosTotais;
    }

    public double getPontosTotais() {
        return pontosTotais;
    }

    public void setPontosTotais(double pontosTotais) {
        this.pontosTotais = pontosTotais;
    }

    public double getPontosMediosPorTreino() {
        return pontosMediosPorTreino;
    }

    public void setPontosMediosPorTreino(double pontosMediosPorTreino) {
        this.pontosMediosPorTreino = pontosMediosPorTreino;
    }
}