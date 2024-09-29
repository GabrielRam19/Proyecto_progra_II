package proyecto.backend.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import proyecto.backend.dtos.FacturaRequestDto;
import proyecto.backend.dtos.FacturaResponseDto;
import proyecto.backend.models.Factura;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.models.Pedido;
import proyecto.backend.models.Producto;
import proyecto.backend.models.Venta;
import proyecto.backend.repositories.FacturaRepository;
import proyecto.backend.repositories.PedidoRepository;
import proyecto.backend.repositories.ProductoRepository;
import proyecto.backend.repositories.VentaRepository;

import java.util.List;

@Service
public class FacturaService {
    private final FacturaRepository facturaRepository;
    private final ModelMapper modelMapper;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    public FacturaService(FacturaRepository facturaRepository,PedidoRepository pedidoRepository, ProductoRepository productoRepository, VentaRepository ventaRepository, ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.modelMapper = modelMapper;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    public List<FacturaResponseDto> findAllFacturas(){
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream().map(factura->modelMapper.map(factura,FacturaResponseDto.class)).toList();
    }

    public FacturaResponseDto findById(Integer id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        return modelMapper.map(factura,FacturaResponseDto.class);
    }

    public FacturaResponseDto saveFactura(FacturaRequestDto facturaDto) throws Exception {
        Factura factura = modelMapper.map(facturaDto,Factura.class);
        return getFacturaResponseDto(factura);
    }

    public FacturaResponseDto updateFactura(Integer id, FacturaRequestDto facturaDto) throws Exception {
        Factura factura = facturaRepository.findById(id).orElse(null);
        modelMapper.map(facturaDto,factura);
        assert factura != null;
        return getFacturaResponseDto(factura);
    }

    private FacturaResponseDto getFacturaResponseDto(Factura factura) throws Exception {
        Pedido pedido = this.pedidoRepository.findById(factura.getIdPedido().getIdPedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
        this.despacharPedido(pedido);
        Factura savedFactura = facturaRepository.save(factura);
        savedFactura.setIdPedido(pedido);
        return modelMapper.map(savedFactura,FacturaResponseDto.class);
    }

    public void deleteFactura(Integer id) {
        facturaRepository.deleteById(id);
    }

    public void despacharPedido(Pedido pedido) throws Exception {
        if(pedido.isDespachado()){
            throw new Exception("Pedido ya despachado, no se puede generar nueva factura!");
        }
        restarExistenciaPedido(pedido);
        pedido.setDespachado(true);
        this.pedidoRepository.save(pedido);
    }

    public void restarExistenciaPedido(Pedido pedido) {
        List<Venta> ventas = this.ventaRepository.findByIdPedidoIdPedido(pedido.getIdPedido());
        ventas.forEach(venta -> {
            Producto producto = this.productoRepository.findById(venta.getIdProducto().getIdProducto())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

            int nuevaExistencia = producto.getExistencia() - venta.getCantidad();

            if (nuevaExistencia < 0) {
                try {
                    throw new Exception("No hay suficiente existencia del producto: "+producto.getNombreProducto());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            producto.setExistencia(nuevaExistencia);
            productoRepository.save(producto);
        });
    }
}
