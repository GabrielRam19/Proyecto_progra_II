package proyecto.backend.services;

import proyecto.backend.dtos.VentaRequestDto;
import proyecto.backend.dtos.VentaResponseDto;
import proyecto.backend.models.Venta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.VentaRepository;

import java.util.List;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ModelMapper modelMapper;
    public VentaService(VentaRepository ventaRepository, ModelMapper modelMapper) {
        this.ventaRepository = ventaRepository;
        this.modelMapper = modelMapper;
    }

    public List<VentaResponseDto> findAllVentas(){
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream().map(venta->modelMapper.map(venta, VentaResponseDto.class)).toList();
    }

    public VentaResponseDto findById(Integer id){
        Venta venta = ventaRepository.findById(id).orElse(null);
        return modelMapper.map(venta, VentaResponseDto.class);
    }

    public VentaResponseDto saveVenta(VentaRequestDto ventaRequestDto){
        Venta venta = modelMapper.map(ventaRequestDto, Venta.class);
        Venta savedVenta = ventaRepository.save(venta);
        return modelMapper.map(savedVenta, VentaResponseDto.class);
    }

    public VentaResponseDto updateVenta(Integer id, VentaRequestDto ventaRequestDto){
        Venta venta = ventaRepository.findById(id).orElse(null);
        modelMapper.map(ventaRequestDto, Venta.class);
        assert venta != null;
        Venta savedVenta = ventaRepository.save(venta);
        return modelMapper.map(savedVenta, VentaResponseDto.class);
    }

    public void deleteVenta(Integer id){
        ventaRepository.deleteById(id);
    }
}
