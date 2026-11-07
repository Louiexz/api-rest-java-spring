package com.example.webapp.webapp.model.DTO.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    private String usuario;
    private String senha;

    public LoginRequest(){}        

    public LoginRequest(
        @NotBlank(message = "Nome ou e-mail é obrigatório.")
        @Size(
            min = 5, max = 254,
            message = "O nome ou e-mail deve ter entre 5 e 254 caracteres.")
        String usuario,

        @NotBlank(message = "Senha é obrigatória.")
        @Size(min = 8, max = 254,
        message = "A senha deve ter entre 8 e 254 caracteres.")
        String senha
    ) {
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters e Setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}