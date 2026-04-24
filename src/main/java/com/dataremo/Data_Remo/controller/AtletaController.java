package com.dataremo.Data_Remo.controller;

import com.dataremo.Data_Remo.model.Atleta;
import com.dataremo.Data_Remo.model.Nivel;
import com.dataremo.Data_Remo.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atletas")
@CrossOrigin
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @GetMapping
    public List<Atleta> listar() {
        return atletaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Atleta atleta) {

        // validações básicas
        if (atleta.getNome() == null || atleta.getNome().isBlank()
                || atleta.getEmail() == null || atleta.getEmail().isBlank()
                || atleta.getSenha() == null || atleta.getSenha().isBlank()) {
            return ResponseEntity.badRequest().body("Nome, email e senha são obrigatórios.");
        }

        // valores padrão (evitar null em colunas NOT NULL)
        if (atleta.getPontosTotais() == null) atleta.setPontosTotais(0.0);
        if (atleta.getTotalTreinos() == null) atleta.setTotalTreinos(0);
        if (atleta.getTempoTotalMinutos() == null) atleta.setTempoTotalMinutos(0);
        if (atleta.getNivel() == null) atleta.setNivel(Nivel.INICIANTE_1);

        try {
            Atleta salvo = atletaRepository.save(atleta);
            return ResponseEntity.ok(salvo);
        } catch (DataIntegrityViolationException e) {
            // duplicidade de email/nome ou violação de constraint
            return ResponseEntity.status(409)
                    .body("Nome ou email já estão em uso, ou dados inválidos.");
        } catch (Exception e) {
            e.printStackTrace(); // loga no console
            return ResponseEntity.status(500).body("Erro ao criar atleta.");
        }
    }

    @GetMapping("/ranking")
    public List<Atleta> rankingGeral() {
        // ordena no banco e filtra só quem tem pontos > 0
        return atletaRepository.findAllByOrderByPontosTotaisDesc()
                .stream()
                .filter(a -> a.getPontosTotais() != null && a.getPontosTotais() > 0)
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