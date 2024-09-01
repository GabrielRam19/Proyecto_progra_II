package proyecto.backend.dtos;

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

    private int cantidad;

    private double precio;
}
