package com.dataremo.Data_Remo.controller;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atletas")
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public List<Atleta> listar() {
        return atletaRepository.findAll();
    }

    @PostMapping
    public Atleta criar(@RequestBody Atleta atleta) {
        return atletaRepository.save(atleta);
    }

    @GetMapping("/ranking")
    public List<Atleta> rankingGeral() {
        return atletaRepository.findAll()
                .stream()
                // ordena por pontosTotais desc, tratando null como 0
                .sorted((a, b) -> {
                    double pa = a.getPontosTotais() != null ? a.getPontosTotais() : 0.0;
                    double pb = b.getPontosTotais() != null ? b.getPontosTotais() : 0.0;
                    return Double.compare(pb, pa); // maior primeiro
                })
                .toList();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        String senha = body.get("senha");

        if (nome == null || senha == null) {
            return ResponseEntity.badRequest().body("Nome e senha são obrigatórios.");
        }

        Atleta atleta = atletaRepository.findByNome(nome);

        if (atleta == null || atleta.getSenha() == null || !atleta.getSenha().equals(senha)) {
            return ResponseEntity.status(401).body("Nome ou senha incorretos.");
        }

        return ResponseEntity.ok(atleta);
    }
}