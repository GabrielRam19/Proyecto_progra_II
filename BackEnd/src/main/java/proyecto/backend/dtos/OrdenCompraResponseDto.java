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
public class OrdenCompraResponseDto {
    private int idIngreso;

    private Integer idProducto;

    private Integer idProveedor;

    private int cantidad;

    private double precio;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
