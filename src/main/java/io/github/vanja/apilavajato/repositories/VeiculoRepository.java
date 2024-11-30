package io.github.vanja.apilavajato.repositories;

import io.github.vanja.apilavajato.entities.Cliente;
import io.github.vanja.apilavajato.entities.Veiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Veiculo v WHERE v.cliente = :cliente")
    void deleteByCliente(@Param("cliente") Cliente cliente);

}
