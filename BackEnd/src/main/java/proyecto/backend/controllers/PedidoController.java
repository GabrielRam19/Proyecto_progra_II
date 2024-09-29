package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.PedidoRequestDto;
import proyecto.backend.dtos.PedidoResponseDto;
import proyecto.backend.services.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponseDto> getAll() {
        return pedidoService.findAllPedidos();
    }

    @GetMapping("/{id}")
    public PedidoResponseDto getById(@PathVariable Integer id) {
        return pedidoService.findPedidoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto create(@RequestBody PedidoRequestDto pedido) {
        return pedidoService.addPedido(pedido);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDto update(@PathVariable Integer id, @RequestBody PedidoRequestDto pedido) {
        return pedidoService.updatePedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        pedidoService.deletePedido(id);
    }
}
