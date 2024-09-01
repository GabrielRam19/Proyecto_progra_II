package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "orden_compra")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_ingreso")
    private int idIngreso;

    @ManyToOne
    @JoinColumn(name = "Id_producto")
    private Producto idProducto;

    @ManyToOne
    @JoinColumn(name = "Id_proveedor")
    private Proveedor idProveedor;

    @Column(name = "Cantidad")
    private int cantidad;

    @Column(name = "Precio")
    private double precio;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
