package proyecto.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto.backend.dtos.FacturaReporteDto;
import proyecto.backend.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer > {
    @Query("SELECT new proyecto.backend.dtos.FacturaReporteDto(f.idFactura, p.idPedido, f.updatedAt, pago.tipoFormaPago, u.nombre, c.nombre)"+
            "FROM Pedido p "+
            "JOIN Factura f ON f.idPedido.idPedido = p.idPedido "+
            "JOIN p.idCliente c "+
            "JOIN p.idUsuario u "+
            "JOIN FormaPago pago ON pago.idFormaPago = f.idFormaPago.idFormaPago "+
            "WHERE f.idFactura = :idFactura")
    FacturaReporteDto findFacturaValues(@Param("idFactura") Integer idFactura);
}
