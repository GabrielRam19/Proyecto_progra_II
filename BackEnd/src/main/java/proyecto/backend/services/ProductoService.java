package proyecto.backend.services;

import proyecto.backend.dtos.ProductoRequestDto;
import proyecto.backend.dtos.ProductoResponseDto;
import proyecto.backend.models.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;
    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductoResponseDto> findAllProductos(){
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(producto->modelMapper.map(producto, ProductoResponseDto.class)).toList();
    }

    public ProductoResponseDto findProductoById(Integer id){
        return modelMapper.map(productoRepository.findById(id).orElse(null), ProductoResponseDto.class);
    }

    public ProductoResponseDto saveProducto(ProductoRequestDto productoRequestDto){
        Producto producto = modelMapper.map(productoRequestDto, Producto.class);
        Producto productoSaved = productoRepository.save(producto);
        return modelMapper.map(productoSaved, ProductoResponseDto.class);
    }

    public ProductoResponseDto updateProducto(Integer id, ProductoRequestDto productoRequestDto){
        Producto producto = productoRepository.findById(id).orElse(null);
        modelMapper.map(productoRequestDto, producto);
        assert producto != null;
        Producto productoSaved = productoRepository.save(producto);
        return modelMapper.map(productoSaved, ProductoResponseDto.class);
    }

    public void deleteProducto(Integer id){
        productoRepository.deleteById(id);
    }
}
