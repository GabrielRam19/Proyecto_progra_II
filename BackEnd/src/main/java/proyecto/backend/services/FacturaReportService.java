package proyecto.backend.services;

import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import proyecto.backend.dtos.FacturaReporteDto;
import proyecto.backend.dtos.VentasFacturaDto;
import proyecto.backend.repositories.PedidoRepository;
import proyecto.backend.repositories.VentaRepository;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacturaReportService {
    private final PedidoRepository pedidoRepository;
    private final VentaRepository ventaRepository;

    public FacturaReportService(PedidoRepository pedidoRepository, VentaRepository ventaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Transactional
    public byte[] generateInvoiceReport(Integer idFactura) throws JRException, IOException {
        // 1. Obtener los datos de la factura
        FacturaReporteDto facturaDto = pedidoRepository.findFacturaValues(idFactura);
        List<VentasFacturaDto> ventasDtoList = ventaRepository.findSalesDataForReport(facturaDto.getIdPedido());

        Resource logoResource = new ClassPathResource("images/ferreteria2.jpg");
        InputStream logoInputStream = logoResource.getInputStream();

        Resource reportResource = new ClassPathResource("reports/factura.jrxml");
        InputStream reportInputStream = reportResource.getInputStream();

        // 2. Preparar los parámetros para el reporte
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyName", "Ferretería-Factura Digital");
        parameters.put("address", "Villa Nueva, Guatemala");
        parameters.put("logo", logoInputStream);
        parameters.put("facturaFecha", facturaDto.getFechaFacturacion().toString());
        parameters.put("cliente", facturaDto.getCliente());
        parameters.put("usuario", facturaDto.getUsuario());
        parameters.put("facturaNumero", String.valueOf(facturaDto.getIdFactura()));
        parameters.put("formaPago", facturaDto.getFormaPago());

        JasperReport jasperReport = JasperCompileManager.compileReport(reportInputStream);

        // 4. Crear el datasource para el detalle de ventas
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ventasDtoList);

        // 5. Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 6. Exportar a PDF y retornar como bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }

}
