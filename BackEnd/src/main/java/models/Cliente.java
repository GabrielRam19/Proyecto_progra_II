package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ClienteRepository")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    @Column(name = "Id_cliente")
    private int idCliente;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "NIT")
    private String nit;
}
