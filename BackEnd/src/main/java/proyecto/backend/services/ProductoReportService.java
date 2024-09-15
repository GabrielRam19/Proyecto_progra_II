package proyecto.backend.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import proyecto.backend.models.Producto;
import proyecto.backend.repositories.ProductoRepository;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductoReportService {

    private final ProductoRepository productoRepository;

    public ProductoReportService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public byte[] exportReport() throws FileNotFoundException, JRException {
        try {
            // Obtener los productos desde la base de datos
            List<Producto> productos = productoRepository.findAll();

            // Verifica que los productos no estén vacíos
            if (productos.isEmpty()) {
                throw new RuntimeException("No hay productos para generar el reporte.");
            }

            // Cargar el archivo JRXML
            File file = ResourceUtils.getFile("classpath:reports/product-inventory-report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Llenar el reporte con los datos de los productos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Sistema de Facturación Ferretería");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Exportar el reporte a PDF en un ByteArrayOutputStream
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                return outputStream.toByteArray();
            }

        } catch (JRException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el reporte: " + e.getMessage(), e);
        }
    }
}
