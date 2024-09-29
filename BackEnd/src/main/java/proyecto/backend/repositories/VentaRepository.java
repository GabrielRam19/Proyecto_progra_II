package proyecto.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto.backend.dtos.VentaReporteDto;
import proyecto.backend.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT new proyecto.backend.dtos.VentaReporteDto(v.idVenta, p.nombreProducto, v.cantidad, p.precioUnitario, v.cantidad * p.precioUnitario, fac.createdAt, fac.idUsuario.nombre, cli.nombre) " +
            "FROM Venta v " +
            "JOIN v.idProducto p " +
            "JOIN v.idPedido ped " + // Cambiar para usar la relación de Venta a Pedido
            "JOIN Factura fac ON fac.idPedido.idPedido = ped.idPedido " + // Usar la relación de Factura a Pedido
            "JOIN ped.idCliente cli " + // Usar la relación de Pedido a Cliente
            "WHERE fac.createdAt BETWEEN :startDate AND :endDate")
    List<VentaReporteDto> findVentasBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Venta> findByIdPedidoIdPedido(Integer idPedido);
}
