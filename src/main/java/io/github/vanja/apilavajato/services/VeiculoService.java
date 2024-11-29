package io.github.vanja.apilavajato.services;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.entities.Veiculo;
import io.github.vanja.apilavajato.repositories.ClienteRepository;
import io.github.vanja.apilavajato.repositories.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;

    public Veiculo save(Veiculo veiculo) {
        // Verifica se o CPF foi fornecido
        if (veiculo.getCliente() == null || veiculo.getCliente().getCpf() == null || veiculo.getCliente().getCpf().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório.");
        }
        // Verifica se o CPF do cliente existe na base de dados
        Cliente clienteExistente = clienteRepository.findByCpf(veiculo.getCliente().getCpf());
        if (clienteExistente == null) {
            throw new RuntimeException("CPF não cadastrado, O CPF deve pertencer a um cliente já existente.");
        }
        // Associa o veículo ao cliente encontrado
        veiculo.setCliente(clienteExistente);
        // Salva o veículo no banco de dados
        return veiculoRepository.save(veiculo);
    }

    public Veiculo findById(Integer id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }
}
