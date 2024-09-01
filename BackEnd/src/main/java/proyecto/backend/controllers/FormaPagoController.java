package proyecto.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import proyecto.backend.dtos.FormaPagoRequestDto;
import proyecto.backend.dtos.FormaPagoResponseDto;
import proyecto.backend.services.FormaPagoService;

import java.util.List;

@RestController
@RequestMapping("/formas-pago")
public class FormaPagoController {
    private final FormaPagoService formapagoService;
    public FormaPagoController(FormaPagoService formapagoService) {
        this.formapagoService = formapagoService;
    }

    @GetMapping
    public List<FormaPagoResponseDto> getAll() {
        return formapagoService.findAllFormaPago();
    }

    @GetMapping("/{id}")
    public FormaPagoResponseDto getById(@PathVariable Integer id) {
        return formapagoService.findFormaPagoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagoResponseDto create(@RequestBody FormaPagoRequestDto formapago) {
        return formapagoService.saveFormaPago(formapago);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagoResponseDto update(@PathVariable Integer id, @RequestBody FormaPagoRequestDto formapago) {
        return formapagoService.updateFormaPago(id, formapago);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        formapagoService.deleteFormaPago(id);
    }
}
