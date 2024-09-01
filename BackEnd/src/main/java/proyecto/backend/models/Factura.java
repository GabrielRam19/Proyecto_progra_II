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
@Table(name = "factura")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_factura")
    private int idFactura;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "Total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "Id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "Id_forma_pago")
    private FormaPago idFormaPago;

    @ManyToOne
    @JoinColumn(name = "Id_cliente")
    private Cliente idCliente;
}
