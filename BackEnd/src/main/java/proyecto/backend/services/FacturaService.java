package proyecto.backend.services;

import proyecto.backend.dtos.FacturaRequestDto;
import proyecto.backend.dtos.FacturaResponseDto;
import proyecto.backend.models.Factura;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.FacturaRepository;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final ModelMapper modelMapper;
    public FacturaService(FacturaRepository facturaRepository, ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.modelMapper = modelMapper;
    }

    public List<FacturaResponseDto> findAllFacturas(){
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream().map(factura->modelMapper.map(factura,FacturaResponseDto.class)).toList();
    }

    public FacturaResponseDto findById(Integer id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        return modelMapper.map(factura,FacturaResponseDto.class);
    }

    public FacturaResponseDto saveFactura(FacturaRequestDto facturaDto) {
        Factura factura = modelMapper.map(facturaDto,Factura.class);
        Factura savedFactura = facturaRepository.save(factura);
        return modelMapper.map(savedFactura,FacturaResponseDto.class);
    }

    public FacturaResponseDto updateFactura(Integer id, FacturaRequestDto facturaDto) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        modelMapper.map(facturaDto,factura);
        assert factura != null;
        Factura savedFactura = facturaRepository.save(factura);
        return modelMapper.map(savedFactura,FacturaResponseDto.class);
    }

    public void deleteFactura(Integer id) {
        facturaRepository.deleteById(id);
    }
}
