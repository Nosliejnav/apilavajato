package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

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

// Teste do método findByCpf
        @Test
        public void testFindByCpf_ClienteExistente() {
            // Arrange
            String cpf = "12345678901";
            Cliente cliente = new Cliente();
            cliente.setCpf(cpf);
            cliente.setNome("João Silva");

            when(clienteRepository.findByCpf(cpf)).thenReturn(cliente);

            // Act
            Cliente result = clienteService.findByCpf(cpf);

            // Assert
            assertNotNull(result);
            assertEquals(cpf, result.getCpf());
            verify(clienteRepository, times(1)).findByCpf(cpf);
        }

    @Test
    public void testFindByCpf_ClienteNaoExistente() {
        // Arrange
        String cpf = "12345678901";

        when(clienteRepository.findByCpf(cpf)).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.findByCpf(cpf);
        });

        assertEquals("Cliente não encontrado para o CPF informado.", exception.getMessage());
        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testFindByCpf_CpfInvalido() {
        // Arrange
        String cpf = "";

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.findByCpf(cpf);
        });

        assertEquals("CPF é obrigatório.", exception.getMessage());
        verify(clienteRepository, never()).findByCpf(anyString());
    }

//Teste do método delete
    @Mock
    private VeiculoRepository veiculoRepository;

    @Test
    public void testDelete_ClienteExistente() {
        // Arrange
        Integer id = 1;
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        clienteService.delete(id);

        // Assert
        verify(veiculoRepository, times(1)).deleteByCliente(cliente);
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    public void testDelete_ClienteNaoExistente() {
        // Arrange
        Integer id = 1;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.delete(id);
        });

        assertEquals("Cliente não encontrado", exception.getReason());
        verify(veiculoRepository, never()).deleteByCliente(any());
        verify(clienteRepository, never()).delete(any());
    }

// Teste do método update
    @Test
    public void testUpdate_ClienteExistente() {
        // Arrange
        Integer id = 1;
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id);
        clienteExistente.setNome("João");

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("João Atualizado");
        clienteAtualizado.setCpf("12345678901");
        clienteAtualizado.setEndereco("Novo Endereço");
        clienteAtualizado.setTelefone("61999998888");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        // Act
        Cliente result = clienteService.update(id, clienteAtualizado);

        // Assert
        assertNotNull(result);
        assertEquals("João Atualizado", result.getNome());
        assertEquals("12345678901", result.getCpf());
        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testUpdate_ClienteNaoExistente() {
        // Arrange
        Integer id = 1;
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("João Atualizado");

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            clienteService.update(id, clienteAtualizado);
        });

        assertEquals("Cliente não encontrado para atualização", exception.getReason());
        verify(clienteRepository, times(1)).findById(id);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

//    @Test
//    void save() {
//    }
//
//    @Test
//    void findByCpf() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void update() {
//    }
}
