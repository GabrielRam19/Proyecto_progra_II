package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venta")
@Getter
@Setter
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
