package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.UsuarioRequestDto;
import proyecto.backend.dtos.UsuarioResponseDto;
import proyecto.backend.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponseDto> getAll() {
        return usuarioService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto getById(@PathVariable Integer id) {
        return usuarioService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto create(@RequestBody UsuarioRequestDto usuario) {
        return usuarioService.saveUser(usuario);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDto update(@PathVariable Integer id, @RequestBody UsuarioRequestDto usuario) {
        return usuarioService.updateUser(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        usuarioService.softDelete(id);
    }
}
