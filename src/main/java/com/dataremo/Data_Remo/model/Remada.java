package com.dataremo.Data_Remo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "remadas")
public class Remada {

    // ID gerado automaticamente pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String atleta;
    private String data;
    private String semana;
    private String mes;
    private String local;
    private Integer duracao;
    private String tipo;
    private Integer intensidade;
    private String comentarios;

    public Remada() {}

    public Remada(String atleta, String data, String semana, String mes,
                  String local, Integer duracao, String tipo,
                  Integer intensidade, String comentarios) {
        this.atleta = atleta;
        this.data = data;
        this.semana = semana;
        this.mes = mes;
        this.local = local;
        this.duracao = duracao;
        this.tipo = tipo;
        this.intensidade = intensidade;
        this.comentarios = comentarios;
    }

    public double calcularPontos() {
        if (duracao == null || intensidade == null || intensidade == 0) return 0.0;
        return Math.round((double) duracao / intensidade * 10.0) / 10.0;
    }

    public Long getId()            { return id; }
    public String getAtleta()      { return atleta; }
    public void setAtleta(String atleta) { this.atleta = atleta; }
    public String getData()        { return data; }
    public void setData(String data) { this.data = data; }
    public String getSemana()      { return semana; }
    public void setSemana(String semana) { this.semana = semana; }
    public String getMes()         { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public String getLocal()       { return local; }
    public void setLocal(String local) { this.local = local; }
    public Integer getDuracao()    { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
    public String getTipo()        { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getIntensidade(){ return intensidade; }
    public void setIntensidade(Integer intensidade) { this.intensidade = intensidade; }
    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    @Override
    public String toString() {
        return String.format(
                "Data: %-12s | Local: %-15s | Duração: %dmin" +
                        " | Tipo: %-12s | Intensidade: %d/10 | Pontos: %.1f",
                data, local, duracao, tipo, intensidade, calcularPontos());
    }
}