package proyecto.backend.controllers;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import proyecto.backend.dtos.ProductoRequestDto;
import proyecto.backend.dtos.ProductoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.services.ProductoReportService;
import proyecto.backend.services.ProductoService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    private final ProductoReportService productoReportService;
    public ProductoController(ProductoService productoService, ProductoReportService productoReportService) {
        this.productoService = productoService;
        this.productoReportService = productoReportService;
    }

    @GetMapping
    public List<ProductoResponseDto> getAll() {
        return productoService.findAllProductos();
    }

    @GetMapping("/{id}")
    public ProductoResponseDto getById(@PathVariable Integer id) {
        return productoService.findProductoById(id);
    }

    @GetMapping("/report/stock")
    public ResponseEntity<ByteArrayResource> existenciaReport() throws JRException, FileNotFoundException {
        // Genera el reporte en un buffer
        byte[] pdfBytes = productoReportService.exportReport();

        // Crea un recurso a partir del array de bytes
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        // Configura las cabeceras para la respuesta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_productos_existencia.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(pdfBytes.length));

        // Devuelve el PDF en la respuesta HTTP
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto create(@Valid @RequestBody ProductoRequestDto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto update(@PathVariable Integer id,@Valid @RequestBody ProductoRequestDto producto) {
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productoService.deleteProducto(id);
    }
}
