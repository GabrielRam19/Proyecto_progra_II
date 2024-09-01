package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.ClienteRequestDto;
import proyecto.backend.dtos.ClienteResponseDto;
import proyecto.backend.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponseDto> getAll() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ClienteResponseDto getById(@PathVariable Integer id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto create(@RequestBody ClienteRequestDto cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDto update(@PathVariable Integer id, @RequestBody ClienteRequestDto cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
    }
}
