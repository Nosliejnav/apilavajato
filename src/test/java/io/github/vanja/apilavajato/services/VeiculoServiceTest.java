package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

        @InjectMocks
        private VeiculoService veiculoService;

        @Mock
        private VeiculoRepository veiculoRepository;

        @Mock
        private ClienteRepository clienteRepository;

//    @Test
//    void save() {
//    }
//

        @Test
        void testSave_Sucesso() {
            // Arrange
            Cliente clienteMock = new Cliente();
            clienteMock.setId(1);
            clienteMock.setCpf("12345678901");

            Veiculo veiculoMock = new Veiculo();
            veiculoMock.setMarca("Toyota");
            veiculoMock.setModelo("Corolla");
            veiculoMock.setPlaca("XYZ-1234");
            veiculoMock.setCliente(clienteMock);

            when(clienteRepository.findByCpf("12345678901")).thenReturn(clienteMock);
            when(veiculoRepository.save(veiculoMock)).thenReturn(veiculoMock);

            // Act
            Veiculo resultado = veiculoService.save(veiculoMock);

            // Assert
            assertNotNull(resultado);
            assertEquals("Toyota", resultado.getMarca());
            assertEquals("12345678901", resultado.getCliente().getCpf());
            verify(clienteRepository, times(1)).findByCpf("12345678901");
            verify(veiculoRepository, times(1)).save(veiculoMock);
        }

        @Test
        void testSave_CpfNaoInformado() {
            // Arrange
            Veiculo veiculoMock = new Veiculo();
            veiculoMock.setMarca("Toyota");
            veiculoMock.setModelo("Corolla");

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> veiculoService.save(veiculoMock));

            assertEquals("CPF é obrigatório.", exception.getMessage());
            verify(clienteRepository, never()).findByCpf(anyString());
            verify(veiculoRepository, never()).save(any(Veiculo.class));
        }

        @Test
        void testSave_CpfNaoCadastrado() {
            // Arrange
            Cliente clienteMock = new Cliente();
            clienteMock.setCpf("12345678901");

            Veiculo veiculoMock = new Veiculo();
            veiculoMock.setMarca("Toyota");
            veiculoMock.setModelo("Corolla");
            veiculoMock.setCliente(clienteMock);

            when(clienteRepository.findByCpf("12345678901")).thenReturn(null);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> veiculoService.save(veiculoMock));

            assertEquals("CPF não cadastrado, O CPF deve pertencer a um cliente já existente.", exception.getMessage());
            verify(clienteRepository, times(1)).findByCpf("12345678901");
            verify(veiculoRepository, never()).save(any(Veiculo.class));
        }

//    @Test
//    void findById() {
//    }

        @Test
        void testFindById_Sucesso() {
            // Arrange
            Veiculo veiculoMock = new Veiculo();
            veiculoMock.setId(1);
            veiculoMock.setMarca("Toyota");
            veiculoMock.setModelo("Corolla");

            when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculoMock));

            // Act
            Veiculo resultado = veiculoService.findById(1);

            // Assert
            assertNotNull(resultado);
            assertEquals(1, resultado.getId());
            assertEquals("Toyota", resultado.getMarca());
            verify(veiculoRepository, times(1)).findById(1);
        }

        @Test
        void testFindById_VeiculoNaoEncontrado() {
            // Arrange
            when(veiculoRepository.findById(1)).thenReturn(Optional.empty());

            // Act & Assert
            ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> veiculoService.findById(1));

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            assertEquals("Veículo não encontrado", exception.getReason());
            verify(veiculoRepository, times(1)).findById(1);
        }
    }

