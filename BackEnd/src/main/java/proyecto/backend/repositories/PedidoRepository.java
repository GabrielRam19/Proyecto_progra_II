package proyecto.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.backend.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer > {

}
