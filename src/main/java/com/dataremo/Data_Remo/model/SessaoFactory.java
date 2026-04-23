package com.dataremo.Data_Remo.model;

public class SessaoFactory {
    public static SessaoTreino criar(String tipo) {
        switch (tipo.toLowerCase()) {
            case "endurance":   return new SessaoEndurance();
            case "intervalado": return new SessaoIntervalado();
            case "tecnico":     return new SessaoTecnica();
            default: throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }
    }
}