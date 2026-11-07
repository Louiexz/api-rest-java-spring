package com.example.webapp.webapp.service.impl.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.cliente.ClienteRequest;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;
import com.example.webapp.webapp.model.Endereco;

@Service
public class UpdateClienteById extends ClienteService { 
    public List<Endereco> checarPutEnderecos(ClienteRequest cliente) {
        List<Endereco> enderecosEncontrados = new ArrayList<>();

        cliente.getCeps().forEach(cep -> {      
            String replacedCep = cep.replaceAll("\\D", "");
            enderecosEncontrados.add(enderecosService.salvar(replacedCep));
        });
        
        return enderecosEncontrados;
    }

    public List<Endereco> checarPatchEnderecos(ClienteRequest cliente, Cliente clienteToUpdate) {
        List<Endereco> enderecos = clienteToUpdate.getEnderecos();

        cliente.getCeps().forEach(cep -> {
            String replacedCep = cep.replaceAll("\\D", "");
            
            boolean existe = enderecos.stream()
                .anyMatch(endereco -> endereco.getCep().equals(replacedCep));

            if (!existe) {
                clienteToUpdate.adicionarEndereco(
                    enderecosService.salvar(replacedCep)
                );
            }
        });
        
        return clienteToUpdate.getEnderecos();
    }
    
    public ClienteResponse execute(Long id, ClienteRequest cliente, boolean isPut) {        
        Cliente clienteToUpdate = getClienteById(id);

        if (cliente.getCeps() != null) {
            if (isPut) {
                clienteToUpdate.setEnderecos(checarPutEnderecos(cliente));
            } else {
                clienteToUpdate.setEnderecos(checarPatchEnderecos(cliente, clienteToUpdate));
            }
        } else {
            if (isPut) {
                clienteToUpdate.setEnderecos(new ArrayList<>());
            }
        }

        if (!cliente.getNome().isBlank() &&
            !cliente.getNome().equals(clienteToUpdate.getNome())) {
                clienteToUpdate.setNome(cliente.getNome());
        }
        if (
            !cliente.getEmail().equals(clienteToUpdate.getEmail()) &&
            !cliente.getEmail().isBlank()) {
                clienteToUpdate.setEmail(cliente.getEmail());
        }
        if  (!cliente.getSenha().equals(clienteToUpdate.getSenha()) &&
            !cliente.getSenha().isBlank()) 
        {
            clienteToUpdate.setSenha(cliente.getSenha());
        }

        clientesRepositorio.save(clienteToUpdate);

        return getClienteResponse(clienteToUpdate);
    }
}