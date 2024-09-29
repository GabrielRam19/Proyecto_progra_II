package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.FacturaRequestDto;
import proyecto.backend.dtos.FacturaResponseDto;
import proyecto.backend.services.FacturaService;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    private final FacturaService facturaService;
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public List<FacturaResponseDto> getAll() {
        return facturaService.findAllFacturas();
    }

    @GetMapping("/{id}")
    public FacturaResponseDto getById(@PathVariable Integer id) {
        return facturaService.findById(id);
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
