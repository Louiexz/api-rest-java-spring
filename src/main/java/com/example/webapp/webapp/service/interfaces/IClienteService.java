package com.example.webapp.webapp.service.interfaces;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteCreateRequest;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteResponse;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteUpdateRequest;
import com.example.webapp.webapp.model.DTO.EnderecoDTO;
import com.example.webapp.webapp.model.Endereco;

public interface IClienteService {
    public void salvar(ClienteCreateRequest entity);

    public ClienteResponse atualizarPorId(Long id, ClienteUpdateRequest cliente);

    public ClienteResponse procurarPorId(Long id);

    public Iterable<ClienteResponse> procurarTodos();;

    public void deletarPorId(Long id);

    public Endereco adicionarEnderecoPorCep(String cep);

    public Endereco atualizarEnderecoPorCep(String cep, EnderecoDTO enderecoDTO);

    public void removerEnderecoPorCep(String cep);
}