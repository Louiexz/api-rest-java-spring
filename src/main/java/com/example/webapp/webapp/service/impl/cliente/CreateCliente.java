package com.example.webapp.webapp.service.impl.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.cliente.ClienteRequest;

@Service
public class CreateCliente extends ClienteService {
    public void salvar(ClienteRequest entity) {
        Cliente cliente = new Cliente(entity.getNome(), entity.getEmail(), entity.getSenha());
        
        entity.getCeps().forEach(cep -> {
            cliente.adicionarEndereco(enderecosService.salvar(cep.replace("\\D", "")));
        });

        if (clientesRepositorio.save(cliente) == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "O cliente não foi cadastrado");
        }
    }
}
