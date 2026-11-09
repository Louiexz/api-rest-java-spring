package com.example.webapp.webapp.model.DTO.cliente;

import org.springframework.util.Assert;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
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
    public LoginRequest {
        Assert.notNull(usuario, "Nome ou e-mail é obrigatório.");
        Assert.notNull(senha, "Senha é obrigatória.");
    }

    // Getters e Setters
    public final String getUsuario() { return usuario; }

    public final String getSenha() { return senha; }
}