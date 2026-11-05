package com.example.webapp.webapp.service.impl.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientNotFoundException extends ResponseStatusException {
    public ClientNotFoundException(Long id) {
        super(
            HttpStatus.NOT_FOUND,
            String.format("Cliente com ID %s não encontrado", id)
        );
    }
}
