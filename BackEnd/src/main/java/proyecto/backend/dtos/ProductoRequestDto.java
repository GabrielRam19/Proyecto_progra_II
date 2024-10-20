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
public class ProductoRequestDto {
    private String nombreProducto;

    @Min(value = 0, message = "El precio unitario no puede ser negativo")
    private double precioUnitario;

    private String descripcion;

    @Min(value = 0, message = "La existencia del producto no puede ser negativa")
    private int existencia;
}
