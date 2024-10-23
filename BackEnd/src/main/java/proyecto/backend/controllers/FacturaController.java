package proyecto.backend.controllers;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.FacturaRequestDto;
import proyecto.backend.dtos.FacturaResponseDto;
import proyecto.backend.services.FacturaReportService;
import proyecto.backend.services.FacturaService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    private final FacturaService facturaService;
    private final FacturaReportService facturaReportService;
    public FacturaController(FacturaService facturaService, FacturaReportService facturaReportService) {
        this.facturaService = facturaService;
        this.facturaReportService = facturaReportService;
    }

    @GetMapping
    public List<FacturaResponseDto> getAll() {
        return facturaService.findAllFacturas();
    }

    @GetMapping("/{id}")
    public FacturaResponseDto getById(@PathVariable Integer id) {
        return facturaService.findById(id);
    }

    @GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarReporteFactura(@RequestParam("idFactura") Integer idFactura) {
        try {
            byte[] pdfBytes = facturaReportService.generateInvoiceReport(idFactura);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "factura_" + idFactura + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (JRException | FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaResponseDto create(@RequestBody FacturaRequestDto factura) throws Exception {
        return facturaService.saveFactura(factura);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FacturaResponseDto update(@PathVariable Integer id, @RequestBody FacturaRequestDto factura) throws Exception {
        return facturaService.updateFactura(id, factura);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        facturaService.deleteFactura(id);
    }
}
