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
public class PedidoResponseDto {
    private int idPedido;

    private int idCliente;

    private int idUsuario;

    private LocalDateTime fechaPedido;

    private double total;

    private boolean despachado;
}
