package com.dataremo.Data_Remo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atletas")
public class Atleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int idade;
    private double altura;
    private String modalidade;
    private String objetivo;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Plano plano;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    private double pontos;
    private int tempoAcumulado;
    private int totalTreinos;

    @ElementCollection
    @CollectionTable(name = "atleta_badges", joinColumns = @JoinColumn(name = "atleta_id"))
    @Column(name = "badge")
    private List<String> badges;

    public Atleta() {
        this.nivel = Nivel.INICIANTE_1;
        this.pontos = 0;
        this.tempoAcumulado = 0;
        this.totalTreinos = 0;
        this.badges = new ArrayList<>();
    }

    public Atleta(String nome, int idade, double altura,
                  String modalidade, String objetivo, Plano plano) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.modalidade = modalidade;
        this.objetivo = objetivo;
        this.plano = plano;
        this.nivel = Nivel.INICIANTE_1;
        this.pontos = 0;
        this.tempoAcumulado = 0;
        this.totalTreinos = 0;
        this.badges = new ArrayList<>();
    }

    public void adicionarPontos(double quantidade) {
        this.pontos += quantidade;
        verificarSubidaNivel();
    }

    public void adicionarTempo(int minutos) { this.tempoAcumulado += minutos; }
    public void incrementarTreinos()        { this.totalTreinos++; }

    private void verificarSubidaNivel() {
        Nivel[] niveis = Nivel.values();
        int indiceAtual = nivel.ordinal();
        if (indiceAtual < niveis.length - 1) {
            Nivel proximo = niveis[indiceAtual + 1];
            boolean pontosOk  = pontos         >= proximo.getPontosParaSubir();
            boolean treinosOk = totalTreinos   >= proximo.getTreinosMinimos();
            boolean tempoOk   = tempoAcumulado >= proximo.getTempoMinimoAcum();
            if (pontosOk && treinosOk && tempoOk) {
                nivel = proximo;
                verificarBadge();
            }
        }
    }

    private void verificarBadge() {
        switch (nivel) {
            case INICIANTE_2:     adicionarBadge("🌊 Primeiros Passos");     break;
            case INICIANTE_3:     adicionarBadge("🚣 Remador Consistente");  break;
            case INTERMEDIARIO_1: adicionarBadge("⚡ Superou o Básico");     break;
            case INTERMEDIARIO_2: adicionarBadge("🔥 Atleta Dedicado");      break;
            case INTERMEDIARIO_3: adicionarBadge("💪 Resistência de Elite"); break;
            case AVANCADO_1:      adicionarBadge("🥇 Remador Avançado");     break;
            case AVANCADO_2:      adicionarBadge("🏅 Alto Rendimento");      break;
            case AVANCADO_3:      adicionarBadge("👑 Mestre das Águas");     break;
        }
    }

    public void adicionarBadge(String badge) { badges.add(badge); }

    // Getters e Setters
    public Long getId()                      { return id; }
    public String getNome()                  { return nome; }
    public void setNome(String nome)         { this.nome = nome; }
    public int getIdade()                    { return idade; }
    public void setIdade(int idade)          { this.idade = idade; }
    public double getAltura()                { return altura; }
    public void setAltura(double altura)     { this.altura = altura; }
    public String getModalidade()            { return modalidade; }
    public void setModalidade(String m)      { this.modalidade = m; }
    public String getObjetivo()              { return objetivo; }
    public void setObjetivo(String o)        { this.objetivo = o; }
    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }
    public String getSenha()                 { return senha; }
    public void setSenha(String senha)       { this.senha = senha; }
    public Plano getPlano()                  { return plano; }
    public void setPlano(Plano plano)        { this.plano = plano; }
    public Nivel getNivel()                  { return nivel; }
    public void setNivel(Nivel nivel)        { this.nivel = nivel; }
    public double getPontos()                { return pontos; }
    public void setPontos(double pontos)     { this.pontos = pontos; }
    public int getTempoAcumulado()           { return tempoAcumulado; }
    public int getTotalTreinos()             { return totalTreinos; }
    public List<String> getBadges()          { return badges; }
    public void setBadges(List<String> b)    { this.badges = b; }

    @Override
    public String toString() {
        return String.format(
                "Atleta: %-10s | Plano: %-5s | Idade: %d | Modalidade: %-8s" +
                        " | Nível: %-15s | Pontos: %.1f | Treinos: %d",
                nome, plano.getNome(), idade, modalidade,
                nivel.getNome(), pontos, totalTreinos);
    }
}