package io.github.vanja.apilavajato.controllers;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.services.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("veiculos")
@AllArgsConstructor
public class VeiculoController {

    private VeiculoService veiculoService ;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo salvar(@RequestBody Veiculo veiculo){
        return veiculoService.salvar(veiculo);
    }

    @GetMapping("{id}")
    public Veiculo obterPorId(@PathVariable Integer id){
        return veiculoService
                .obterPorId(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Veiculo não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Veiculo atualizar (@PathVariable Integer id,
                            @RequestBody Veiculo veiculo){
        return veiculoService
                .obterPorId(id)
                .map(veiculoExistente -> {
                    veiculo.setId(veiculoExistente.getId());
                    veiculoService.salvar(veiculo);
                    return veiculoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Veiculo não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id){
        veiculoService.obterPorId(id)
            .map(veiculo ->{
                veiculoService.deletar(veiculo);
                return veiculo;
            })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Veiculo não encontrado"));
    }

    @GetMapping
    public List<Veiculo> filtrarTodos(Veiculo filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Veiculo> veiculos = veiculoService.filtrarTodos(example);

        if (veiculos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado");
        }

        return veiculos;
    }
}

