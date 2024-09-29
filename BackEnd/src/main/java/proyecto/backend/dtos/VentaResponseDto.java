package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponseDto {
    private int idVenta;

    private Integer idPedido;

    private Integer idProducto;

    private int cantidad;
}
