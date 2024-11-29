package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Optional<Veiculo> obterPorId(Integer id){
        return repository.findById(id);
    }

    //    public void Atualizar(Veiculo veiculo){
//        if(Veiculo.getId() == null){
//            throw new IllegalArgumentException("Para atualizar, é necessário que o veiculo já esteja salvo. ");
//
//            repository.save(veiculo);
//        }
//    }

    public void deletar(Veiculo veiculo){
        repository.delete(veiculo);
    }

    public List<Veiculo> filtrarTodos(Example<Veiculo> example) {
        return repository.findAll(example);
    }

}
