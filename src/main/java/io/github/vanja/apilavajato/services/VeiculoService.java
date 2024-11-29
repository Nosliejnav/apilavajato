package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Optional<Veiculo> obterPorId(Integer id){
        return veiculoRepository.findById(id);
    }

    //    public void Atualizar(Veiculo veiculo){
//        if(Veiculo.getId() == null){
//            throw new IllegalArgumentException("Para atualizar, é necessário que o veiculo já esteja salvo. ");
//
//            veiculoRepository.save(veiculo);
//        }
//    }

    public void deletar(Veiculo veiculo){
        veiculoRepository.delete(veiculo);
    }

    public List<Veiculo> filtrarTodos(Example<Veiculo> example) {
        return veiculoRepository.findAll(example);
    }

}
