package io.github.vanja.apilavajato.repositories;

import io.github.vanja.apilavajato.entities.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void deveSalvarClienteComSucesso() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setCpf("12345678901");
        cliente.setEndereco("Quadra 01 Casa 01");
        cliente.setTelefone("61988776644");

        // Act
        Cliente clienteSalvo = clienteRepository.save(cliente);
        System.out.println("Cliente salvo " + clienteSalvo);

        // Assert
        assertNotNull(clienteSalvo.getId());
        assertEquals("João Silva", clienteSalvo.getNome());
        assertEquals("12345678901", clienteSalvo.getCpf());
        assertEquals("Quadra 01 Casa 01", clienteSalvo.getEndereco());
        assertEquals("61988776644", clienteSalvo.getTelefone());
    }

//    @Test
//    void findByCpf() {
//    }
}









