package com.projeto.recrutamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        // Correção para a versão nova: passamos o serviço diretamente dentro do construtor
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Páginas HTML e arquivos estáticos livres para os candidatos
                .requestMatchers("/", "/index.html", "/cadastrar.html", "/candidatar.html", "/recrutador.html").permitAll()
                
                // Rotas da API das Vagas
                .requestMatchers(HttpMethod.GET, "/api/vagas").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/vagas").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/vagas/**").authenticated()
                
                // Rotas da API de Candidaturas
                .requestMatchers(HttpMethod.POST, "/api/candidaturas").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/candidaturas/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/candidaturas/**").authenticated()
                
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/recrutador.html", true) // Leva o recrutador direto pro painel ao logar
                .permitAll()
            )
            .httpBasic(basic -> basic.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

