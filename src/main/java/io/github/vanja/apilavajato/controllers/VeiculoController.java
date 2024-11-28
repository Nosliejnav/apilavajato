package io.github.vanja.apilavajato.controllers;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.services.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("veiculos")
@AllArgsConstructor
public class VeiculoController {

    private VeiculoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo salvar(@RequestBody Veiculo veiculo){
        return service.salvar(veiculo);
    }

}
