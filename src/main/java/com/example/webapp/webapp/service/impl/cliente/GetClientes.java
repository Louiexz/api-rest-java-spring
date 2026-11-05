package com.example.webapp.webapp.service.impl.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;

@Service
public class GetClientes extends ClienteService {    
    public Iterable<ClienteResponse> procurarTodos() {
        List<ClienteResponse> clientesLista = new ArrayList<>();

        Iterable<Cliente> clientes = clientesRepositorio.findAll();

        for(Cliente cliente: clientes) {
            clientesLista.add(getClienteResponse(cliente));
        }

        return clientesLista;
    }
}
