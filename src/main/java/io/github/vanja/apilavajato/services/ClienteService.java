package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service


@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

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

    public void delete(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        // Remove os veículos associados ao cliente
        veiculoRepository.deleteByCliente(cliente);
        // Remove o cliente
        clienteRepository.delete(cliente);
    }

    public Cliente update(Integer id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    // Atualiza os campos do cliente existente com os valores recebidos
                    clienteExistente.setNome(clienteAtualizado.getNome());
                    clienteExistente.setCpf(clienteAtualizado.getCpf());
                    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
                    clienteExistente.setTelefone(clienteAtualizado.getTelefone());
                    // Salva o cliente atualizado
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

}
