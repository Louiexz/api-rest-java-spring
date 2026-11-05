package com.example.webapp.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.webapp.model.Endereco;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {}