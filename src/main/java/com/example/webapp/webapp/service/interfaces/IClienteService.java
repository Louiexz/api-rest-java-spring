package com.example.webapp.webapp.service.interfaces;
import com.example.webapp.webapp.model.DTO.EnderecoDTO;
import com.example.webapp.webapp.model.DTO.cliente.ClienteRequest;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;
import com.example.webapp.webapp.model.Endereco;

public abstract class IClienteService {
    public void salvar(ClienteRequest entity){};

    public ClienteResponse atualizarPorId(Long id, ClienteRequest cliente){
        return null;
    };

    public ClienteResponse procurarPorId(Long id){
        return null;
    };

    public Iterable<ClienteResponse> procurarTodos(){
        return null;
    };

    public void deletarPorId(Long id){};

    public Endereco adicionarEnderecoPorCep(String cep){
        return null;
    };

    public Endereco atualizarEnderecoPorCep(String cep, EnderecoDTO enderecoDTO){
        return null;
    };

    public abstract void removerEnderecoPorCep(String cep);
}