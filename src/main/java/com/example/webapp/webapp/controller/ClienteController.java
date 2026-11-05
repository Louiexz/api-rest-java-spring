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

import com.example.webapp.webapp.model.DTO.cliente.ClienteRequest;
import com.example.webapp.webapp.model.DTO.cliente.ClienteResponse;
import com.example.webapp.webapp.service.impl.cliente.CreateCliente;
import com.example.webapp.webapp.service.impl.cliente.DeleteClienteById;
import com.example.webapp.webapp.service.impl.cliente.GetClienteById;
import com.example.webapp.webapp.service.impl.cliente.GetClientes;
import com.example.webapp.webapp.service.impl.cliente.UpdateClienteById;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private CreateCliente createClienteService;    
    @Autowired
    private GetClienteById getClienteByIdService;
    @Autowired
    private DeleteClienteById deleteClienteByIdService;
    @Autowired
    private GetClientes getClientesService;
    @Autowired
    private UpdateClienteById updateClienteByIdService;

    @GetMapping
    public ResponseEntity<Iterable<ClienteResponse>> procurarTodos() {
        return ResponseEntity.ok(getClientesService.procurarTodos());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody ClienteRequest cliente) {
        createClienteService.salvar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> procurarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(getClienteByIdService.procurarPorId(id));
    }
    
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPatch(@PathVariable Long id,
                                    @RequestBody ClienteRequest cliente) {
        updateClienteByIdService.atualizarPorId(id, cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPut(@PathVariable Long id,
                                  @Valid @RequestBody ClienteRequest cliente) {
        updateClienteByIdService.atualizarPorId(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        deleteClienteByIdService.deletarPorId(id);
    }
}