package com.projeto.recrutamento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.recrutamento.model.Vaga;
import com.projeto.recrutamento.repository.VagaRepository;

@RestController
@RequestMapping("/api/vagas")
@CrossOrigin(origins = "*") // Permite que a nossa futura tela HTML converse com o Java sem bloqueios de segurança
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    // Rota para LISTAR todas as vagas do site
    @GetMapping
    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    // Rota para CADASTRAR uma vaga nova
    @PostMapping
    public Vaga criarNova(@RequestBody Vaga vaga) {
        return vagaRepository.save(vaga);
    }

        // Rota para EXCLUIR uma vaga pelo ID
    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public void excluir(@org.springframework.web.bind.annotation.PathVariable Long id) {
        vagaRepository.deleteById(id);
    }
    

}
