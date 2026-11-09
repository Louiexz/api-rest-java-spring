package com.example.webapp.webapp.service.impl.cliente;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class DeleteClienteById extends ClienteService {
    
    public void execute(UUID id) {        
        getClienteById(id);

        clientesRepositorio.deleteById(id);
    }
}
