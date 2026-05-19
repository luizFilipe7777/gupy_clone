package com.projeto.recrutamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.projeto.recrutamento.model.Usuario;
import com.projeto.recrutamento.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Retorna o formato que o Spring Security exige para fazer o login
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("RECRUTADOR")
                .build();
    }
}
