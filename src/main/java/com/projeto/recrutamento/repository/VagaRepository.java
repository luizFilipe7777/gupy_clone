package com.projeto.recrutamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.recrutamento.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    // Esta linha vazia já estende centenas de comandos prontos do Spring!
}
