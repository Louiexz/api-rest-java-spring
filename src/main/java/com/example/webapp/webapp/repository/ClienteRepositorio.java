package com.example.webapp.webapp.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.webapp.webapp.model.Cliente;
@Repository
public interface ClienteRepositorio extends CrudRepository<Cliente, UUID> {
    Cliente findByNome(String user);
    Cliente findByEmail(String user);
}