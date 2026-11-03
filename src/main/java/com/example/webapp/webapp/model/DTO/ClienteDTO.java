package com.example.webapp.webapp.model.DTO;

import java.util.List;

import com.example.webapp.webapp.model.Endereco;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {
    public static class ClienteCreateRequest {
        private String nome;
        private String email;
        private String senha;
        private List<String> enderecos;

        public ClienteCreateRequest(){}        

        public ClienteCreateRequest(
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
                String> enderecos
            ) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.enderecos = enderecos;
        }

        // Getters e Setters
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }

        public List<String> getEnderecos() { return enderecos; }
        public void setEnderecos(List<String> enderecos) { this.enderecos = enderecos; }

        @Override
        public String toString() {
            return "ClienteCreateRequest [nome=" + nome + ", email=" + email + ", senha=" + senha + ", enderecos="
                    + enderecos + "]";
        }
    }

    public static class ClienteUpdateRequest {
        private String nome;
        private String email;
        private String senha;
        private List<String> enderecos;

        public ClienteUpdateRequest(){}

        public ClienteUpdateRequest(
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
            String> enderecos
        ) {
            this.enderecos = enderecos;
            this.email = email;
            this.nome = nome;
            this.senha = senha;
        }

        // Getters e Setters
        public String getNome() { return nome; }
        private void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        private void setEmail(String email) { this.email = email; }

        public String getSenha() { return senha; }
        private void setSenha(String senha) { this.senha = senha; }

        public List<String> getEnderecos() { return enderecos; }
        private void setEnderecos(List<String> enderecos) { this.enderecos = enderecos; }
    }

    public static record ClienteResponse(
        String nome,
        String email,
        List<Endereco> enderecos
    ) {}   
}