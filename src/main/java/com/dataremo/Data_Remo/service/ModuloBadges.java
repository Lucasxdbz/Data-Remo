package com.dataremo.Data_Remo.service;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.model.Observador;
import com.dataremo.Data_Remo.model.Remada;

public class ModuloBadges implements Observador {

    @Override
    public void atualizar(String mensagem, Remada remada, Atleta atleta) {
        if (!atleta.getBadges().isEmpty()) {
            System.out.println("\n┌─ BADGES CONQUISTADAS ─┐");
            for (String badge : atleta.getBadges())
                System.out.println("  " + badge);
        }
    }
}