package com.example.webapp.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.webapp.model.Endereco;
import com.example.webapp.webapp.service.impl.endereco.EnderecoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("endereco")
@SecurityRequirement(name = "bearerAuth")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    private final String REGEX_CEP = "^\\d{5}-?\\d{3}$";
    private final String REGEX_CEP_MESSAGE = "CEP inválido. Utilize 12345678 ou 12345-678.";

    @GetMapping
    public ResponseEntity<Iterable<Endereco>> procurarTodos() {
        return ResponseEntity.ok(enderecoService.procurarTodos());
    }

    @PostMapping("/{cep}")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(
            @PathVariable
            @Pattern(
                regexp = REGEX_CEP,
                message = REGEX_CEP_MESSAGE)
            String cep) {
        enderecoService.salvar(cep);
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> procurarPorId(
            @PathVariable
            @Pattern(
                regexp = REGEX_CEP,
                message = REGEX_CEP_MESSAGE)
            String cep) {
        return ResponseEntity.ok(enderecoService.procurarPorCep(cep));
    }
}