package com.example.webapp.webapp.model.DTO.cliente;

import java.util.List;

import com.example.webapp.webapp.model.Endereco;

public record ClienteResponse(
    String nome,
    String email,
    List<Endereco> enderecos
) {}   