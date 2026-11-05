package com.example.webapp.webapp.service.interfaces;
import com.example.webapp.webapp.model.Endereco;

public interface IEnderecoService {
    public Endereco salvar(String Cep);

    public Endereco procurarPorCep(String Cep);

    public Iterable<Endereco> procurarTodos();;

    public void deletarPorCep(String Cep);
}
