package com.example.webapp.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.webapp.webapp.model.Endereco;
import com.example.webapp.webapp.repository.EnderecoRepository;
import com.example.webapp.webapp.service.interfaces.IEnderecoService;
import com.example.webapp.webapp.service.interfaces.ViaCepService;

@Service
public class EnderecoService implements IEnderecoService {
    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private EnderecoRepository enderecosRepositorio;

    @Override
    public Endereco salvar(String cep) {
        var replacedCep = cep.replaceAll("\\D", "");
        
        Endereco endereco = procurarPorCep(replacedCep);

        endereco.setCep(replacedCep);

        enderecosRepositorio.save(endereco);

        return endereco;
    }

    public void getCepNaoEncontradoException(String cep) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "CEP não encontrado: " + cep);
    }

    @Override
    public Endereco procurarPorCep(String cep) {
        Endereco endereco = enderecosRepositorio.findById(cep)
            .orElseGet(() -> viaCepService.consultarCep(cep));

        if (endereco == null && endereco.getCep() == null) {
            getCepNaoEncontradoException(cep);
        }

        return endereco;
    }

    @Override
    public Iterable<Endereco> procurarTodos() {        
        return enderecosRepositorio.findAll();
    }

    @Override
    public void deletarPorCep(String cep) {
        enderecosRepositorio.deleteById(cep);
    }
}