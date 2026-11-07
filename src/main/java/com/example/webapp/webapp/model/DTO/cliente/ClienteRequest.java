package com.example.webapp.webapp.model.DTO.cliente;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteRequest {
    private String nome;
    private String email;
    private String senha;
    private List<String> ceps;

    public ClienteRequest(){}        

    public ClienteRequest(
        @NotBlank(message = "Nome é obrigatório.")
        @Size(
            min = 5, max = 254,
            message = "O nome deve ter entre 5 e 254 caracteres.")
        String nome,
        
        @NotBlank(message = "E-mail é obrigatório.")
        @Size(min = 3, max = 254,
            message = "O e-mail deve ter entre 3 e 254 caracteres.")
        @Email(message = "E-mail inválido.")
        String email,

        @NotBlank(message = "Senha é obrigatória.")
        @Size(min = 8, max = 254,
        message = "A senha deve ter entre 8 e 254 caracteres.")
        String senha,

        @NotEmpty(message = "Informe um ou mais CEPs.")
        List<@NotBlank(message = "O CEP não pode ser vazio.")
        @Pattern(
            regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Utilize 12345678 ou 12345-678.")
        String> ceps
    ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ceps = ceps;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<String> getCeps() { return ceps; }
    public void setCeps(List<String> ceps) { this.ceps = ceps; }
}