package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaResponseDto {
    private int idFactura;

    private double total;

    private Integer idUsuario;

    private Integer idFormaPago;

    private Integer idCliente;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
