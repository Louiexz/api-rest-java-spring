package com.example.webapp.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.webapp.model.DTO.cliente.LoginRequest;
import com.example.webapp.webapp.service.impl.cliente.autenticacao.ClienteLogin;
import com.example.webapp.webapp.service.impl.cliente.autenticacao.ClienteLogout;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("auth")
@SecurityRequirement(name = "bearerAuth")
public class AutenticacaoController {
    private final ClienteLogin logIn;
    private final ClienteLogout loginOut;

    public AutenticacaoController(ClienteLogin logIn, ClienteLogout loginOut) {
        this.logIn = logIn;
        this.loginOut = loginOut;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest login) {
        return logIn.execute(login.getUsuario(), login.getSenha());
    }
    
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String autorizacao) {
        String token = autorizacao.replace("Bearer ", "");
        loginOut.execute(token);
    }
}
