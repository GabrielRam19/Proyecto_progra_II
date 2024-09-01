package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.OrdenCompraRequestDto;
import proyecto.backend.dtos.OrdenCompraResponseDto;
import proyecto.backend.services.OrdenCompraService;

import java.util.List;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {
    private final OrdenCompraService ordenCompraService;
    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    @GetMapping
    public List<OrdenCompraResponseDto> getAll() {
        return ordenCompraService.findAllOrdenCompras();
    }

    @GetMapping("/{id}")
    public OrdenCompraResponseDto getById(@PathVariable Integer id) {
        return ordenCompraService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenCompraResponseDto create(@RequestBody OrdenCompraRequestDto ordenCompra) {
        return ordenCompraService.saveOrdenCompra(ordenCompra);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenCompraResponseDto update(@PathVariable Integer id, @RequestBody OrdenCompraRequestDto ordenCompra) {
        return ordenCompraService.updateOrdenCompra(id, ordenCompra);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        ordenCompraService.deleteOrdenCompra(id);
    }
}
