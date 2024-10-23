package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venta")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_venta")
    private int idVenta;

    @ManyToOne
    @JoinColumn(name = "Id_pedido")
    private Pedido idPedido;

    @ManyToOne
    @JoinColumn(name = "Id_producto")
    private Producto idProducto;

    @Column(name = "Cantidad")
    private int cantidad;
}
