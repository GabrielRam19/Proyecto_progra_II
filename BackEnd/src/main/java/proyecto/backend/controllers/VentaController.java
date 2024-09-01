package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.VentaRequestDto;
import proyecto.backend.dtos.VentaResponseDto;
import proyecto.backend.services.VentaService;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    private final VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<VentaResponseDto> getAll() {
        return ventaService.findAllVentas();
    }

    @GetMapping("/{id}")
    public VentaResponseDto getById(@PathVariable Integer id) {
        return ventaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto create(@RequestBody VentaRequestDto venta) {
        return ventaService.saveVenta(venta);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDto update(@PathVariable Integer id, @RequestBody VentaRequestDto venta) {
        return ventaService.updateVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
    }
}
