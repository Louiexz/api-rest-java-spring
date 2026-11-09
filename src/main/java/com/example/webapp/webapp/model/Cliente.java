package com.example.webapp.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {    
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    
    @OneToMany
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nome, String email, String senha) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        setSenha(senha);
    }

    public final UUID getId() {
        return id;
    }
    public final void setId(UUID id) {
        this.id = id;
    }
    public final String getNome() {
        return nome;
    }
    public final void setNome(String nome) {
        this.nome = nome;
    }
    public final String getEmail() {
        return email;
    }
    public final void setEmail(String email) {
        this.email = email;
    }
    public final String getSenha() {
        return senha;
    }
    public final void setSenha(String senha) {        
        this.senha = passwordEncoder().encode(senha);
    }
    public final List<Endereco> getEnderecos() {
        return enderecos;
    }
    public final void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    public final void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    public final void removerEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8(); // cost factor 12 (~300ms)
    }
}