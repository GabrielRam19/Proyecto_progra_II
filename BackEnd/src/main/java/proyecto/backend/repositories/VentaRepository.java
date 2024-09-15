package proyecto.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto.backend.dtos.VentaReporteDto;
import proyecto.backend.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT new proyecto.backend.dtos.VentaReporteDto(v.idVenta, p.nombreProducto, v.cantidad, p.precioUnitario, v.cantidad * p.precioUnitario, v.idFactura.createdAt, v.idFactura.idUsuario.nombre, v.idFactura.idCliente.nombre) " +
            "FROM Venta v JOIN v.idProducto p " +
            "WHERE v.idFactura.createdAt BETWEEN :startDate AND :endDate")
    List<VentaReporteDto> findVentasBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
