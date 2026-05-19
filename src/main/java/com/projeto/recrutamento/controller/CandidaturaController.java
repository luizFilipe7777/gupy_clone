package com.projeto.recrutamento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.recrutamento.model.Candidatura;
import com.projeto.recrutamento.repository.CandidaturaRepository;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin(origins = "*")
public class CandidaturaController {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    // Rota para o candidato enviar o currículo para uma vaga
    @PostMapping
    public Candidatura seCandidatar(@RequestBody Candidatura candidatura) {
        return candidaturaRepository.save(candidatura);
    }

    // Rota que a empresa vai usar para ver quem se candidatou na vaga X
    @GetMapping("/vaga/{vagaId}")
    public List<Candidatura> listarPorVaga(@PathVariable Long vagaId) {
        return candidaturaRepository.findByVagaId(vagaId);
    }

    // Rota para mudar o status do candidato (Aprovar/Reprovar)
    @PutMapping("/{id}/status")
    public Candidatura atualizarStatus(@PathVariable Long id, @RequestBody String novoStatus) {
        Candidatura candidatura = candidaturaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));
        
        // Remove aspas extras que o javascript costuma enviar no texto puro
        candidatura.setStatus(novoStatus.replace("\"", "")); 
        return candidaturaRepository.save(candidatura);
    }
}
