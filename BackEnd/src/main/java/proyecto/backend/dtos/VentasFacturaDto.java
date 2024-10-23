package proyecto.backend.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VentasFacturaDto {
    private String nombreProducto;
    private int cantidad;
    private Double precioUnitario;
    private Double total;

    public VentasFacturaDto(String nombreProducto, int cantidad, Double precioUnitario) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = (precioUnitario != null) ? cantidad * precioUnitario : 0.0;
    }
}
