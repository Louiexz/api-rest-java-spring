package com.example.webapp.webapp.service.impl.cliente.autenticacao;

import org.springframework.stereotype.Service;

import com.example.webapp.webapp.infraestrutura.JwtTokenService;
import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.service.impl.cliente.ClientNotFoundException;
import com.example.webapp.webapp.service.impl.cliente.ClienteService;

@Service
public class ClienteLogin extends ClienteService {
    private final JwtTokenService jwtToken;

    public ClienteLogin(JwtTokenService jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String execute(String usuario, String senha) {
        Cliente cliente = clientesRepositorio.findByNome(usuario);

        if (cliente == null) {
            cliente = clientesRepositorio.findByEmail(usuario);            
        }

        if (cliente != null) {
            if(Cliente.passwordEncoder().matches(senha, cliente.getSenha())) {
                return jwtToken.generateToken(cliente.getId().toString(), usuario);
            }
        }

        throw new ClientNotFoundException();
    }
}