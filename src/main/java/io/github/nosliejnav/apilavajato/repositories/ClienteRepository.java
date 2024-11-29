package io.github.nosliejnav.apilavajato.repositories;

import io.github.nosliejnav.apilavajato.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
