package proyecto.backend.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import proyecto.backend.dtos.VentaRequestDto;
import proyecto.backend.dtos.VentaResponseDto;
import proyecto.backend.models.Pedido;
import proyecto.backend.models.Producto;
import proyecto.backend.models.Venta;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.PedidoRepository;
import proyecto.backend.repositories.ProductoRepository;
import proyecto.backend.repositories.VentaRepository;

import java.util.List;
import java.util.Objects;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;
    public VentaService(VentaRepository ventaRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.ventaRepository = ventaRepository;
        this.modelMapper = modelMapper;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public List<VentaResponseDto> findAllVentas(){
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream().map(venta->modelMapper.map(venta, VentaResponseDto.class)).toList();
    }

    public VentaResponseDto findById(Integer id){
        Venta venta = ventaRepository.findById(id).orElse(null);
        return modelMapper.map(venta, VentaResponseDto.class);
    }

    public List<VentaResponseDto> findByPedidoId(Integer idPedido){
        List<Venta> ventas = ventaRepository.findByIdPedidoIdPedido(idPedido);
        return ventas.stream().map(venta->modelMapper.map(venta, VentaResponseDto.class)).toList();
    }

    public VentaResponseDto saveVenta(VentaRequestDto ventaRequestDto) throws Exception {
        Venta venta = modelMapper.map(ventaRequestDto, Venta.class);

        Producto producto = getProducto(venta);
        Pedido pedido = getPedido(venta);

        if (pedido.isDespachado()) {
            throw new Exception("Pedido ya despachado, no se puede añadir ventas!");
        }

        if (validateStock(producto, venta.getCantidad())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay suficiente existencia del producto");
        }

        Venta savedVenta = ventaRepository.save(venta);

        updateTotal(savedVenta, producto);

        return modelMapper.map(savedVenta, VentaResponseDto.class);
    }

    public VentaResponseDto updateVenta(Integer id, VentaRequestDto ventaRequestDto) throws Exception {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));

        Producto producto = getProducto(venta);
        Pedido pedido = getPedido(venta);

        double totalAnterior = venta.getCantidad() * producto.getPrecioUnitario();

        modelMapper.map(ventaRequestDto, venta);
        venta.setCantidad(ventaRequestDto.getCantidad());

        if (validateStock(producto, venta.getCantidad())) {
            throw new Exception("No hay suficiente existencia del producto: " + producto.getNombreProducto());
        }

        Venta savedVenta = ventaRepository.save(venta);

        double totalNuevo = savedVenta.getCantidad() * producto.getPrecioUnitario();

        pedido.setTotal(pedido.getTotal() - totalAnterior + totalNuevo);
        pedidoRepository.save(pedido);

        return modelMapper.map(savedVenta, VentaResponseDto.class);
    }

    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));

        Producto producto = getProducto(venta);
        Pedido pedido = getPedido(venta);
        double totalVenta = venta.getCantidad() * producto.getPrecioUnitario();
        pedido.setTotal(pedido.getTotal() - totalVenta);
        pedidoRepository.save(pedido);

        ventaRepository.deleteById(id);
    }

    private Producto getProducto(Venta venta) {
        return productoRepository.findById(venta.getIdProducto().getIdProducto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    private Pedido getPedido(Venta venta) {
        return pedidoRepository.findById(venta.getIdPedido().getIdPedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));
    }

    public void updateTotal(Venta venta, Producto producto) {
        Pedido pedido = pedidoRepository.findById(venta.getIdPedido().getIdPedido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        double totalVenta = venta.getCantidad() * producto.getPrecioUnitario();
        pedido.setTotal(pedido.getTotal() + totalVenta);
        pedidoRepository.save(pedido);
    }

    private boolean validateStock(Producto producto, int cantidadSolicitada) {
        return producto.getExistencia() < cantidadSolicitada;
    }
}
