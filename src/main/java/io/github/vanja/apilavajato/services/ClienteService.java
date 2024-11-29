package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

//    public void update(Cliente cliente){
//        if(Cliente.getId() == null){
//            throw new IllegalArgumentException("Para atualizar, é necessário que o cliente já esteja salvo. ");
//
//            repository.save(cliente);
//        }
//    }
//

    public void delete(Cliente cliente){
        repository.delete(cliente);
    }

//    public List<Cliente> find(String nome, String cpf){
//        if (nome != null && cpf != null){
//            return repository.findByNomeAndCpf(nome, cpf);
//        }
//        if (nome != null){
//            return repository.findByNome(nome);
//        }
//        if (cpf != null){
//            return repository.findByCpf(cpf);
//        }
//
////        return repository.findAll();
//    }


}
