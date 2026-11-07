package com.example.webapp.webapp.service.impl.cliente.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.webapp.webapp.infraestrutura.JwtTokenService;
import com.example.webapp.webapp.service.impl.cliente.ClienteService;

@Service
public class ClienteLogout extends ClienteService {
    @Autowired 
    private JwtTokenService jwtToken;

    public void execute(String token) {
        if (jwtToken.isTokenValid(token)) {
            jwtToken.removeToken(token);
        } else {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Token jwt invalido")
            );
        }
    }
}