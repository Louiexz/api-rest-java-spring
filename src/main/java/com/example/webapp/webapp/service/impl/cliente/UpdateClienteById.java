package com.example.webapp.webapp.service.impl.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.cliente.ClienteRequest;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;
import com.example.webapp.webapp.model.Endereco;

@Service
public class UpdateClienteById extends ClienteService {
    
    public ClienteResponse atualizarPorId(Long id, ClienteRequest cliente) {
        Cliente clienteToUpdate = getClienteById(id);

        if (cliente.getEnderecos() != null) {
            List<Endereco> enderecos = clienteToUpdate.getEnderecos();

            for (String cepIn : cliente.getEnderecos()) {
                boolean existe = enderecos.stream()
                    .anyMatch(endereco -> endereco.getCep().equals(cepIn));

                if (!existe) {
                    clienteToUpdate.adicionarEndereco(
                        adicionarEnderecoPorCep(cepIn)
                    );
                }
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
