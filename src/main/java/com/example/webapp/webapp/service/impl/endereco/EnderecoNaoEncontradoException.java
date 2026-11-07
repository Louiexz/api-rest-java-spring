package com.example.webapp.webapp.service.impl.endereco;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EnderecoNaoEncontradoException extends ResponseStatusException {
    public EnderecoNaoEncontradoException (String cep) {
        super(
                HttpStatus.NOT_FOUND,
                "Cep não encontrado: " + cep);
    }
}
