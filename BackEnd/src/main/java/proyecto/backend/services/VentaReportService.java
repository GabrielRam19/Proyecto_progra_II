package proyecto.backend.services;

import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import proyecto.backend.dtos.VentaReporteDto;
import proyecto.backend.repositories.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VentaReportService {
    private final VentaRepository ventaRepository;
    public VentaReportService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional
    public byte[] generateSalesReport(LocalDateTime startDate, LocalDateTime endDate) throws JRException, FileNotFoundException {
        List<VentaReporteDto> ventas = ventaRepository.findVentasBetweenDates(startDate, endDate);
        if(ventas.isEmpty()) {
            throw new JRException("No hay ventas para generar un reporte!");
        }

        // Cargar el archivo .jasper compilado
        File file = ResourceUtils.getFile("classpath:reports/sales-report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        String reportPeriod = "Periodo de ventas del "+startDate.toLocalDate()+" al "+endDate.toLocalDate();
        // Preparar los datos para el reporte
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ventas);
        // Preparar parámetros
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reportPeriod", reportPeriod);
        InputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:images/ferreteria.jpg").getAbsolutePath());
        parameters.put("companyName", "Construye Facil S.A");
        parameters.put("header", "Reporte de Ventas");
        parameters.put("logo", logoStream);
        // Generar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar el reporte a PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        return outputStream.toByteArray();
    }
}
