package com.example.webapp.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.example.webapp.webapp.service.impl.cliente.UpdateClienteById;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {
    private final CreateCliente createClienteService;
    private final GetClienteById getClienteByIdService;
    private final DeleteClienteById deleteClienteByIdService;
    private final UpdateClienteById updateClienteByIdService;

    public ClienteController(CreateCliente createClienteService, DeleteClienteById deleteClienteByIdService, GetClienteById getClienteByIdService, UpdateClienteById updateClienteByIdService) {
        this.createClienteService = createClienteService;
        this.deleteClienteByIdService = deleteClienteByIdService;
        this.getClienteByIdService = getClienteByIdService;
        this.updateClienteByIdService = updateClienteByIdService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@Valid @RequestBody ClienteRequest cliente) {
        createClienteService.execute(cliente);
    }

    @GetMapping()
    public ResponseEntity<ClienteResponse> procurarPorId(Authentication authentication) {
        return ResponseEntity.ok(
            getClienteByIdService.execute(Long.valueOf((String) authentication.getPrincipal())));
    }
    
    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPatch(@RequestBody ClienteRequest cliente,
            Authentication authentication) {
        updateClienteByIdService.execute(Long.valueOf((String) authentication.getPrincipal()), cliente, false);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void atualizarPorIdPut(@Valid @RequestBody ClienteRequest cliente,
            Authentication authentication) {
        updateClienteByIdService.execute(
            Long.valueOf((String) authentication.getPrincipal()), cliente, true);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPorId(Authentication authentication) {
        deleteClienteByIdService.execute(Long.valueOf((String) authentication.getPrincipal()));
    }
}