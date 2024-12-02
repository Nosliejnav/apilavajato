package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

//    @Test
//    void save() {
//    }

        @InjectMocks
        private ClienteService clienteService;

        @Mock
        private ClienteRepository clienteRepository;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testSave_NovoCliente_SalvoComSucesso() {
            // Arrange
            Cliente cliente = new Cliente();
            cliente.setCpf("12345678901");
            cliente.setNome("João Silva");

            when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(null); // Simula cliente inexistente
            when(clienteRepository.save(cliente)).thenReturn(cliente); // Simula o comportamento do save

            // Act
            Cliente result = clienteService.save(cliente);

            // Assert
            assertNotNull(result);
            assertEquals(cliente.getCpf(), result.getCpf());
            verify(clienteRepository, times(1)).save(cliente); // Verifica que o método save foi chamado
        }

        @Test
        public void testSave_ClienteJaExiste_ExcecaoLancada() {
            // Arrange
            Cliente cliente = new Cliente();
            cliente.setCpf("12345678901");

            when(clienteRepository.findByCpf(cliente.getCpf())).thenReturn(cliente); // Simula cliente existente

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                clienteService.save(cliente);
            });

            assertEquals("Cliente já existe!", exception.getMessage());
            verify(clienteRepository, never()).save(cliente); // Verifica que o método save nunca foi chamado
        }
    }
