package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
