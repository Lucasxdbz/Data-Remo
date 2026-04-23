package com.dataremo.Data_Remo.controller;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.model.Remada;
import com.dataremo.Data_Remo.model.ResumoMensalDTO;
import com.dataremo.Data_Remo.repository.AtletaRepository;
import com.dataremo.Data_Remo.repository.RemadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remadas")
public class RemadaController {

    @Autowired
    private RemadaRepository remadaRepository;

    @Autowired
    private AtletaRepository atletaRepository;

    @PostMapping
    public Remada registrar(@RequestBody Remada remada) {
        remadaRepository.save(remada);

        Atleta atleta = atletaRepository.findByNome(remada.getAtleta());
        if (atleta != null) {
            atleta.adicionarPontos(remada.calcularPontos());
            atleta.adicionarTempo(remada.getDuracao() != null ? remada.getDuracao() : 0);
            atleta.incrementarTreinos();
            atletaRepository.save(atleta); // persiste as mudanças no banco
        }

        return remada;
    }

    @GetMapping("/atleta/{nome}")
    public List<Remada> listarPorAtleta(@PathVariable String nome) {
        return remadaRepository.findByAtleta(nome);
    }

    @GetMapping("/atleta/{nome}/semana/{semana}")
    public List<Remada> listarPorSemana(
            @PathVariable String nome,
            @PathVariable String semana) {
        return remadaRepository.findByAtletaAndSemana(nome, semana);
    }

    @GetMapping("/atleta/{nome}/mes/{mes}/{ano}")
    public List<Remada> listarPorMes(
            @PathVariable String nome,
            @PathVariable String mes,
            @PathVariable String ano) {
        return remadaRepository.findByAtletaAndMes(nome, mes + "/" + ano);
    }

    @GetMapping("/atleta/{nome}/resumo-mensal/{mes}/{ano}")
    public ResumoMensalDTO resumoMensal(
            @PathVariable String nome,
            @PathVariable String mes,
            @PathVariable String ano) {
        List<Remada> remadas = remadaRepository.findByAtletaAndMes(nome, mes + "/" + ano);

        ResumoMensalDTO resumo = new ResumoMensalDTO();
        resumo.setAtleta(nome);
        resumo.setMes(mes + "/" + ano);

        int totalTreinos = remadas.size();
        int minutosTotais = 0;
        double pontosTotais = 0.0;

        for (Remada r : remadas) {
            if (r.getDuracao() != null) minutosTotais += r.getDuracao();
            pontosTotais += r.calcularPontos();
        }

        resumo.setTotalTreinos(totalTreinos);
        resumo.setMinutosTotais(minutosTotais);
        resumo.setPontosTotais(pontosTotais);
        resumo.setPontosMediosPorTreino(
                totalTreinos > 0 ? pontosTotais / totalTreinos : 0.0
        );

        return resumo;
    }
}