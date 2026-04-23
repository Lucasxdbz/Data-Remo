package com.dataremo.Data_Remo.controller;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .sorted((a, b) -> Double.compare(b.getPontos(), a.getPontos()))
                .toList();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        String senha = body.get("senha");

        Atleta atleta = atletaRepository.findByNome(nome);
        if (atleta == null || !atleta.getSenha().equals(senha)) {
            return ResponseEntity.status(401).body("Nome ou senha incorretos.");
        }
        return ResponseEntity.ok(atleta);
    }

}