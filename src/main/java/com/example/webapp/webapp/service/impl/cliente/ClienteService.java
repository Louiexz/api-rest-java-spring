package com.example.webapp.webapp.service.impl.cliente;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.EnderecoDTO;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;
import com.example.webapp.webapp.model.Endereco;
import com.example.webapp.webapp.repository.ClienteRepositorio;
import com.example.webapp.webapp.service.impl.endereco.EnderecoService;

public class ClienteService {
    @Autowired
    protected ClienteRepositorio clientesRepositorio;

    @Autowired
    protected EnderecoService enderecosService;

    public Cliente getClienteById(Long id) {
        Cliente cliente = clientesRepositorio
            .findById(id)
            .orElseThrow(() -> new ClientNotFoundException(id));
        return cliente;
    }

    public ClienteResponse getClienteResponse(Cliente cliente){
        return new ClienteResponse(cliente.getNome(), cliente.getEmail(), cliente.getEnderecos());
    }

    public Endereco atualizarEnderecoPorCep(String cep, EnderecoDTO enderecoDTO) {
        return null;
    }/*
        Cliente cliente = clientesRepositorio.findById(User.id).get();
        Endereco enderecoAtualizado = null;

        for (Endereco endereco : cliente.getEndereco()) {
            if (endereco.getCep().equals(cep)) {
                if (!enderecoDTO.logradouro.isBlank()) {
                    endereco.setLogradouro(enderecoDTO.logradouro);
                }
                if (!enderecoDTO.complemento.isBlank()) {
                    endereco.setComplemento(enderecoDTO.complemento);
                }
                if (!enderecoDTO.unidade.isBlank()) {
                    endereco.setUnidade(enderecoDTO.unidade);
                }
                if (!enderecoDTO.bairro.isBlank()) {
                    endereco.setBairro(enderecoDTO.bairro);
                }
                enderecoAtualizado = endereco;
                break;
            }
        }
        
        clientesRepositorio.save(cliente);
        return enderecoAtualizado;
    }*/ 

    public void removerEnderecoPorCep(String cep) {}
    /*
        Cliente cliente = clientesRepositorio.findById(User.id).get();

        for(var endereco : cliente.getEndereco()) {
            if (endereco.getCep().equals(cep)) {
                cliente.removerEndereco(endereco);
                break;
            };
        }

        clientesRepositorio.save(cliente);
    } */
}