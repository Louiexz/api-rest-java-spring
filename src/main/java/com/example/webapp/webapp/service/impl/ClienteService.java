package com.example.webapp.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.webapp.webapp.model.Cliente;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteCreateRequest;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteResponse;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteUpdateRequest;
import com.example.webapp.webapp.model.DTO.EnderecoDTO;
import com.example.webapp.webapp.model.Endereco;
import com.example.webapp.webapp.repository.ClienteRepository;
import com.example.webapp.webapp.service.interfaces.IClienteService;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository clientesRepositorio;

    @Autowired
    private EnderecoService enderecosService;

    @Override
    public void salvar(ClienteCreateRequest entity) {
        Cliente cliente = new Cliente(entity.getNome(), entity.getEmail(), entity.getSenha());

        entity.getEnderecos().forEach(endereco -> {
            cliente.adicionarEndereco(adicionarEnderecoPorCep(endereco.replace("-", "")));
        });

        if (clientesRepositorio.save(cliente) == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cliente não foi cadastrado");
        }
    }

    public Cliente getClienteById(Long id) {
        Cliente cliente = clientesRepositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Cliente com ID %s não encontrado", id)
            ));

        return cliente;
    }

    @Override
    public ClienteResponse atualizarPorId(Long id, ClienteUpdateRequest cliente) {
        Cliente clienteToUpdate = getClienteById(id);

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

        if (!cliente.getNome().equals(clienteToUpdate.getNome()) &&
            !cliente.getEmail().equals(clienteToUpdate.getEmail()) &&
            !cliente.getSenha().equals(clienteToUpdate.getSenha()))
        {
            clienteToUpdate.setNome(!cliente.getNome().isBlank() ?
                cliente.getNome() :
                clienteToUpdate.getNome());
        
            clienteToUpdate.setEmail(!cliente.getEmail().isBlank() ?
                cliente.getEmail() :
                clienteToUpdate.getEmail());

            clienteToUpdate.setSenha(!cliente.getSenha().isBlank() ?
                cliente.getSenha() :
                clienteToUpdate.getSenha());
        }        

        clientesRepositorio.save(clienteToUpdate);

        return getClienteResponse(clienteToUpdate);
    }

    public ClienteResponse getClienteResponse(Cliente cliente){
        return new ClienteResponse(cliente.getNome(), cliente.getEmail(), cliente.getEnderecos());
    }

    @Override
    public ClienteResponse procurarPorId(Long id) {
        Cliente cliente = getClienteById(id);

        return getClienteResponse(cliente);
    }

    @Override
    public Iterable<ClienteResponse> procurarTodos() {
        List<ClienteResponse> clientesLista = new ArrayList<>();

        Iterable<Cliente> clientes = clientesRepositorio.findAll();

        for(Cliente cliente: clientes) {
            clientesLista.add(getClienteResponse(cliente));
        }

        return clientesLista;
    }

    @Override
    public void deletarPorId(Long id) {        
        clientesRepositorio.deleteById(id);
    }

    @Override
    public Endereco adicionarEnderecoPorCep(String cep) {
        return enderecosService.salvar(cep);
    }

    @Override
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

    @Override
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