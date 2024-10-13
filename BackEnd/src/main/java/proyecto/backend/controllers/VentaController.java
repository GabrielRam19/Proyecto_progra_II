package proyecto.backend.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import proyecto.backend.dtos.VentaRequestDto;
import proyecto.backend.dtos.VentaResponseDto;
import proyecto.backend.services.VentaReportService;
import proyecto.backend.services.VentaService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private final VentaService ventaService;
    private final VentaReportService ventaReportService;
    public VentaController(VentaService ventaService, VentaReportService ventaReportService) {
        this.ventaService = ventaService;
        this.ventaReportService = ventaReportService;
    }

    @GetMapping
    public List<VentaResponseDto> getAll() {
        return ventaService.findAllVentas();
    }

    @GetMapping("/{id}")
    public VentaResponseDto getById(@PathVariable Integer id) {
        return ventaService.findById(id);
    }

    @GetMapping("/pedido/{idPedido}")
    public List<VentaResponseDto> getByPedidoId(@PathVariable Integer idPedido) {
        return ventaService.findByPedidoId(idPedido);
    }

    @GetMapping("/report/sales")
    public void generateSalesReport(@RequestParam("startDate") String startDateStr,
                                    @RequestParam("endDate") String endDateStr,
                                    HttpServletResponse response) throws IOException {
        LocalDateTime startDate = LocalDateTime.parse(startDateStr);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr);

        try {
            byte[] pdfContent = ventaReportService.generateSalesReport(startDate, endDate);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=sales_report.pdf");
            response.setContentLength(pdfContent.length);

            response.getOutputStream().write(pdfContent);
            response.getOutputStream().flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating report");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto create(@RequestBody VentaRequestDto venta) throws Exception {
        return ventaService.saveVenta(venta);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto update(@PathVariable Integer id, @RequestBody VentaRequestDto venta) throws Exception {
        return ventaService.updateVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
    }
}
