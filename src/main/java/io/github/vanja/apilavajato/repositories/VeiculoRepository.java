package io.github.vanja.apilavajato.repositories;

import io.github.vanja.apilavajato.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
