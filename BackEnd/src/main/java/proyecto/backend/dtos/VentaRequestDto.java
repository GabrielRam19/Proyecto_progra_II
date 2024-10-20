package proyecto.backend.dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequestDto {
    private Integer idPedido;

    private Integer idProducto;

    @Min(value = 0, message = "La cantidad a vender no puede ser negativa")
    private int cantidad;
}
