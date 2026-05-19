package com.projeto.recrutamento.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projeto.recrutamento.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Comando customizado para o Java buscar o usuário pelo nome na hora do login
    Optional<Usuario> findByUsername(String username);
}
