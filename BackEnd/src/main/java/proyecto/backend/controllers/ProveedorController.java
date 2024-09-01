package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.ProveedorRequestDto;
import proyecto.backend.dtos.ProveedorResponseDto;
import proyecto.backend.services.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<ProveedorResponseDto> getAll() {
        return proveedorService.findAllProveedor();
    }

    @GetMapping("/{id}")
    public ProveedorResponseDto getById(@PathVariable Integer id) {
        return proveedorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProveedorResponseDto create(@RequestBody ProveedorRequestDto proveedor) {
        return proveedorService.saveProveedor(proveedor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProveedorResponseDto update(@PathVariable Integer id, @RequestBody ProveedorRequestDto proveedor) {
        return proveedorService.updateProveedor(proveedor, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        proveedorService.deleteProveedor(id);
    }
}
