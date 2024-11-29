package io.github.vanja.apilavajato.repositories;

import io.github.vanja.apilavajato.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByCpf(String cpf);
}
