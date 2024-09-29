package proyecto.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyecto.backend.models.Cliente;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDto {
    private int idCliente;

    private int idUsuario;
}
