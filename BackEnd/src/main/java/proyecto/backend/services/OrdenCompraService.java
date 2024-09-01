package proyecto.backend.services;

import proyecto.backend.dtos.OrdenCompraRequestDto;
import proyecto.backend.dtos.OrdenCompraResponseDto;
import proyecto.backend.models.OrdenCompra;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.OrdenCompraRepository;

import java.util.List;

@Service
public class OrdenCompraService {
    private final OrdenCompraRepository ordenCompraRepository;
    private final ModelMapper modelMapper;
    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository, ModelMapper modelMapper) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrdenCompraResponseDto> findAllOrdenCompras() {
        List<OrdenCompra> ordenCompras = ordenCompraRepository.findAll();
        return ordenCompras.stream().map(ordenCompra -> modelMapper.map(ordenCompra, OrdenCompraResponseDto.class)).toList();
    }

    public OrdenCompraResponseDto findById(Integer id) {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(id).orElse(null);
        return modelMapper.map(ordenCompra, OrdenCompraResponseDto.class);
    }

    public OrdenCompraResponseDto saveOrdenCompra(OrdenCompraRequestDto ordenCompraDto) {
        OrdenCompra ordenCompra = modelMapper.map(ordenCompraDto, OrdenCompra.class);
        OrdenCompra savedOrdenCompra = ordenCompraRepository.save(ordenCompra);
        return modelMapper.map(savedOrdenCompra, OrdenCompraResponseDto.class);
    }

    public OrdenCompraResponseDto updateOrdenCompra(Integer id, OrdenCompraRequestDto ordenCompraDto) {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(id).orElse(null);
        modelMapper.map(ordenCompraDto, ordenCompra);
        assert ordenCompra != null;
        OrdenCompra savedOrdenCompra = ordenCompraRepository.save(ordenCompra);
        return modelMapper.map(savedOrdenCompra, OrdenCompraResponseDto.class);
    }

    public void deleteOrdenCompra(Integer id) {
        ordenCompraRepository.deleteById(id);
    }
}
