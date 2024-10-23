package proyecto.backend.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FacturaReporteDto {
    private int idFactura;
    private int idPedido;
    private LocalDateTime fechaFacturacion;
    private String formaPago;
    private String usuario;
    private String cliente;
}
