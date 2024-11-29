package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(Integer id){
        return clienteRepository.findById(id);
    }

    public void delete(Cliente cliente){
        clienteRepository.delete(cliente);
    }

    public List<Cliente> findAll(Example<Cliente> example) {
        return clienteRepository.findAll(example);
    }

}
