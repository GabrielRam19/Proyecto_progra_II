package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.backend.models.Pedido;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaResponseDto {
    private int idFactura;

    private Integer idUsuario;

    private Integer idFormaPago;

    private PedidoResponseDto pedido;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
