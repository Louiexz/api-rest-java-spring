package com.example.webapp.webapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.webapp.webapp.model.Endereco;
import com.example.webapp.webapp.repository.EnderecoRepository;
import com.example.webapp.webapp.service.interfaces.ViaCepService;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTests {
    @Mock
    private ViaCepService viaCepService;

    @Mock
    private EnderecoRepository enderecosRepositorio;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void rejectsUnknownCepWithoutPersistingAddress() {
        when(viaCepService.consultarCep("99999999")).thenReturn(new Endereco());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> enderecoService.salvar("99999999"));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(enderecosRepositorio, never()).save(any(Endereco.class));
    }

    @Test
    void savesAddressReturnedByViaCep() {
        Endereco endereco = new Endereco();
        endereco.setCep("01001-000");
        endereco.setLogradouro("Praça da Sé");
        when(viaCepService.consultarCep("01001000")).thenReturn(endereco);

        Endereco savedAddress = enderecoService.salvar("01001000");

        assertEquals("01001000", savedAddress.getCep());
        assertEquals("Praça da Sé", savedAddress.getLogradouro());
        verify(enderecosRepositorio).save(endereco);
    }
}
