package com.dataremo.Data_Remo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atletas")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private Double altura;
    private String modalidade;
    private String objetivo;
    private String plano;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Nivel nivel = Nivel.INICIANTE_1;

    @Column(nullable = false)
    private Double pontosTotais = 0.0;

    @Column(nullable = false)
    private Integer totalTreinos = 0;

    @Column(nullable = false)
    private Integer tempoTotalMinutos = 0;

    public Atleta() {}

    // GETTERS / SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }

    public Double getPontosTotais() { return pontosTotais; }
    public void setPontosTotais(Double pontosTotais) { this.pontosTotais = pontosTotais; }

    public Integer getTotalTreinos() { return totalTreinos; }
    public void setTotalTreinos(Integer totalTreinos) { this.totalTreinos = totalTreinos; }

    public Integer getTempoTotalMinutos() { return tempoTotalMinutos; }
    public void setTempoTotalMinutos(Integer tempoTotalMinutos) { this.tempoTotalMinutos = tempoTotalMinutos; }
}