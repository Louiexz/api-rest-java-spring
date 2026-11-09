package com.example.webapp.webapp.service.impl.cliente;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;

@Service
public class GetClienteById extends ClienteService {
    public ClienteResponse execute(UUID id) {
        Cliente cliente = getClienteById(id);

        return getClienteResponse(cliente);
    }    
}   
