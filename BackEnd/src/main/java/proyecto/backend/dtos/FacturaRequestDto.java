package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequestDto {
    private double total;

    private Integer idUsuario;

    private Integer idFormaPago;

    private Integer idCliente;
}
