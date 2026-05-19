package com.projeto.recrutamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_candidaturas")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vaga_id")
    private Long vagaId; 
    
    private String nomeCandidato;
    private String email;
    private String telefone;
    private String linkCurriculo;
    private String status = "Em Análise"; // Garante que toda inscrição comece com esse status no banco

    // --- GETTERS E SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }

    public String getNomeCandidato() { return nomeCandidato; }
    public void setNomeCandidato(String nomeCandidato) { this.nomeCandidato = nomeCandidato; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getLinkCurriculo() { return linkCurriculo; }
    public void setLinkCurriculo(String linkCurriculo) { this.linkCurriculo = linkCurriculo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
