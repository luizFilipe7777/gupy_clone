package com.projeto.recrutamento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.recrutamento.model.Candidatura;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    // Esta linha abaixo vai nos permitir buscar no futuro todos os candidatos de uma vaga específica!
    List<Candidatura> findByVagaId(Long vagaId);
}
