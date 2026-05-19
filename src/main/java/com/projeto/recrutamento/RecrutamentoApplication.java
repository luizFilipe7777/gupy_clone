package com.projeto.recrutamento;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.projeto.recrutamento.model.Usuario;
import com.projeto.recrutamento.repository.UsuarioRepository;


@SpringBootApplication
public class RecrutamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecrutamentoApplication.class, args);
    }

    // Esse bloco roda toda vez que o sistema liga e garante que exista um recrutador cadastrado
    @Bean
    public CommandLineRunner inicializarUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                // Criptografa a senha "123" usando BCrypt antes de salvar no banco!
                admin.setPassword(passwordEncoder.encode("123"));
                usuarioRepository.save(admin);
                System.out.println("✅ Usuário administrador criado com sucesso! (User: admin / Senha: 123)");
            }
        };
    }
}
