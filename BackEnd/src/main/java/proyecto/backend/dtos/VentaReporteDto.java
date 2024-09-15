package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaReporteDto {
    private int idVenta;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double total;
    private LocalDateTime fecha;
    private String usuario;
    private String cliente;
}
