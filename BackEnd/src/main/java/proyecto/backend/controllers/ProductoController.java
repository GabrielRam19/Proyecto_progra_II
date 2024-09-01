package proyecto.backend.controllers;

import proyecto.backend.dtos.ProductoRequestDto;
import proyecto.backend.dtos.ProductoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResponseDto> getAll() {
        return productoService.findAllProductos();
    }

    @GetMapping("/{id}")
    public ProductoResponseDto getById(@PathVariable Integer id) {
        return productoService.findProductoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto create(@RequestBody ProductoRequestDto producto) {
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDto update(@PathVariable Integer id, @RequestBody ProductoRequestDto producto) {
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productoService.deleteProducto(id);
    }
}
