package com.example.webapp.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteCreateRequest;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteResponse;
import com.example.webapp.webapp.model.DTO.ClienteDTO.ClienteUpdateRequest;
import com.example.webapp.webapp.service.impl.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Iterable<ClienteResponse>> procurarTodos() {
        return ResponseEntity.ok(clienteService.procurarTodos());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody ClienteCreateRequest cliente) {
        clienteService.salvar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> procurarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.procurarPorId(id));
    }
    
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPatch(@PathVariable Long id, @RequestBody ClienteUpdateRequest cliente) {
        clienteService.atualizarPorId(id, cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPut(@PathVariable Long id, @Valid @RequestBody ClienteUpdateRequest cliente) {
        clienteService.atualizarPorId(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        clienteService.deletarPorId(id);
    }
}