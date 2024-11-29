package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public Cliente save(Cliente cliente){
        Cliente clienteExiste = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExiste != null) {
            throw new RuntimeException("Cliente já existe!");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente findByCpf(String cpf) {
        // Verifica se o CPF foi fornecido
        if (cpf == null || cpf.isEmpty()) {
            throw new RuntimeException("CPF é obrigatório.");
        }
        // Busca o cliente no banco de dados pelo CPF
        Cliente cliente = clienteRepository.findByCpf(cpf);
        // Se o cliente não for encontrado, lança um erro
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado para o CPF informado.");
        }
        return cliente;
    }
}
