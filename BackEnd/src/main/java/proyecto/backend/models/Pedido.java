package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_pedido")
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "Id_cliente")
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "Id_usuario")
    private Usuario idUsuario;

    @Column(name = "Fecha_pedido")
    @UpdateTimestamp
    private LocalDateTime fechaPedido;

    @Column(name = "Total")
    private double total;

    @Column(name = "Despachado")
    private boolean despachado;
}
