package com.infnet.web_rest_carlos_ferreira_tp3.services;

import com.infnet.web_rest_carlos_ferreira_tp3.models.Cliente;
import com.infnet.web_rest_carlos_ferreira_tp3.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testBuscarClientePorId() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNome("Teste Cliente");
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(clienteId);

        assertEquals(cliente, clienteEncontrado.orElse(null));
        verify(clienteRepository, times(1)).findById(clienteId);
    }

}
