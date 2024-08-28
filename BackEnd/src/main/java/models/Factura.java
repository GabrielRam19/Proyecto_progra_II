package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Factura")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue
    @Column(name = "Id_factura")
    private int idFactura;

    @Column(name = "Fecha")
    private LocalDateTime fecha;

    @Column(name = "Total")
    private double total;

    @Column(name = "Id_usuario")
    private int idUsuario;

    @Column(name = "Id_forma_pago")
    private int idFormaPago;

    @Column(name = "Id_cliente")
    private int idCliente;
}
