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
public class OrdenCompraRequestDto {
    private Integer idProducto;

    private Integer idProveedor;

    @Min(value = 0, message = "La cantidad a ingresar a bodega no puede ser negativa")
    private int cantidad;

    @Min(value = 0, message = "El precio al que se compro el producto no puede ser negativo")
    private double precio;
}
