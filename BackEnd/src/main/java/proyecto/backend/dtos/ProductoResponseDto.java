package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDto {
    private int idProducto;

    private String nombreProducto;

    private double precioUnitario;

    private String descripcion;

    private int existencia;
}
