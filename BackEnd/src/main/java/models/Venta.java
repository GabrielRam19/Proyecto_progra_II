package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue
    @Column(name = "Id_venta")
    private int idVenta;

    @Column(name = "Id_factura")
    private int idFactura;

    @Column(name = "Id_producto")
    private int idProducto;

    @Column(name = "Cantidad")
    private int cantidad;
}
