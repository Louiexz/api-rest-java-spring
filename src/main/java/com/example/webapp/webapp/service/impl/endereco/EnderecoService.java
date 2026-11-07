package com.example.webapp.webapp.service.impl.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Endereco procurarPorCep(String cep) {
        Endereco endereco = enderecosRepositorio
            .findById(cep)
            .orElseGet(() -> { 
                try {
                    return viaCepService.consultarCep(cep);
                } catch (Exception e) {
                    throw new EnderecoNaoEncontradoException(cep);
                }
            });
        return endereco;
    }

    @Override
    public Iterable<Endereco> procurarTodos() {        
        return enderecosRepositorio.findAll();
    }

    @Override
    public void deletarPorCep(String cep) {
        String replacedCep = cep.replaceAll("\\D", "");
        if (procurarPorCep(replacedCep) != null) {
            enderecosRepositorio.deleteById(replacedCep);
        }
    }
}