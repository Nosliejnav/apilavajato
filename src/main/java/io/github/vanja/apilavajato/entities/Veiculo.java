package io.github.vanja.apilavajato.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_veiculo")
    private Integer id;

    private String marca;

    private String modelo;

    private String placa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)  // Faz a ligação entre o Veículo e o Cliente
    private Cliente cliente;  // Relacionamento com Cliente

}
