package io.github.vanja.apilavajato.controllers;

import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.services.VeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    private VeiculoService veiculoService ;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Veiculo salvar(@RequestBody Veiculo veiculo){
        return veiculoService.save(veiculo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Integer id) {
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(veiculo);
    }

//    // Endpoint para listar todos os veículos
//    @GetMapping
//    public ResponseEntity<List<Veiculo>> getAllVeiculos() {
//        List<Veiculo> veiculos = veiculoService.findAll();
//        return ResponseEntity.ok(veiculos);
//    }

}

