package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue
    @Column(name = "Id_producto")
    private int idProducto;

    @Column(name = "Nombre_producto")
    private String nombreProducto;

    @Column(name = "Precio_unitario")
    private double precioUnitario;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Existencia")
    private int existencia;
}
