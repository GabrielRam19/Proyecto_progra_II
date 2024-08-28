package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Proveedor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue
    @Column(name = "Id_proveedor")
    private int id_proveedor;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Direccion")
    private String direccion;
}
