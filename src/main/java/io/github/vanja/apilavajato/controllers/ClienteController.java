package io.github.vanja.apilavajato.controllers;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save ( @RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    // Endpoint para buscar cliente pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<?> getClienteByCpf(@PathVariable String cpf) {
        try {
            // Chama o serviço para buscar o cliente pelo CPF
            Cliente cliente = clienteService.findByCpf(cpf);
            // Retorna o cliente encontrado com sucesso
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Retorna a mensagem de erro caso o CPF não seja válido ou o cliente não seja encontrado
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}