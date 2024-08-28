package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orden_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompra {
    @Id
    @GeneratedValue
    @Column(name = "Id_ingreso")
    private int idIngreso;

    @Column(name = "Fecha_ingreso")
    private String fechaIngreso;

    @Column(name = "Id_producto")
    private int idProducto;

    @Column(name = "Id_proveedor")
    private int idProveedor;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "Precio")
    private double precio;
}
