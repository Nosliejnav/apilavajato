package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> findById(Integer id){
        return repository.findById(id);
    }

    public void delete(Cliente cliente){
        repository.delete(cliente);
    }

    public List<Cliente> findAll(Example<Cliente> example) {
        return repository.findAll(example);
    }

}
