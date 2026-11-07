package com.example.webapp.webapp.service.impl.cliente;

import org.springframework.stereotype.Service;

@Service
public class DeleteClienteById extends ClienteService {
    
    public void execute(Long id) {        
        getClienteById(id);

        clientesRepositorio.deleteById(id);
    }
}
