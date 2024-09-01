package proyecto.backend.services;

import proyecto.backend.dtos.ProveedorRequestDto;
import proyecto.backend.dtos.ProveedorResponseDto;
import proyecto.backend.models.Proveedor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.ProveedorRepository;

import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;
    private final ModelMapper modelMapper;
    public ProveedorService(ProveedorRepository proveedorRepository, ModelMapper modelMapper) {
        this.proveedorRepository = proveedorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProveedorResponseDto> findAllProveedor(){
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream().map(proveedor->modelMapper.map(proveedor, ProveedorResponseDto.class)).toList();
    }

    public ProveedorResponseDto findById(Integer id){
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        return modelMapper.map(proveedor, ProveedorResponseDto.class);
    }

    public ProveedorResponseDto saveProveedor(ProveedorRequestDto proveedorDto){
        Proveedor proveedor = modelMapper.map(proveedorDto, Proveedor.class);
        Proveedor proveedorSaved = proveedorRepository.save(proveedor);
        return modelMapper.map(proveedorSaved, ProveedorResponseDto.class);
    }

    public ProveedorResponseDto updateProveedor(ProveedorRequestDto proveedorDto, Integer id){
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        modelMapper.map(proveedorDto, proveedor);
        assert proveedor != null;
        Proveedor proveedorUpdated = proveedorRepository.save(proveedor);
        return modelMapper.map(proveedorUpdated, ProveedorResponseDto.class);
    }

    public void deleteProveedor(Integer id){
        proveedorRepository.deleteById(id);
    }
}
