package proyecto.backend.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.dtos.PedidoRequestDto;
import proyecto.backend.dtos.PedidoResponseDto;
import proyecto.backend.models.Pedido;
import proyecto.backend.repositories.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    public PedidoService(PedidoRepository pedidoRepository, ModelMapper modelMapper) {
        this.pedidoRepository = pedidoRepository;
        this.modelMapper = modelMapper;
    }

    public List<PedidoResponseDto> findAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> modelMapper.map(pedido, PedidoResponseDto.class)).toList();
    }

    public PedidoResponseDto findPedidoById(Integer id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        return modelMapper.map(pedido, PedidoResponseDto.class);
    }

    public PedidoResponseDto addPedido(PedidoRequestDto pedidoDto) {
        Pedido pedido = modelMapper.map(pedidoDto, Pedido.class);
        pedido = pedidoRepository.save(pedido);
        return modelMapper.map(pedido, PedidoResponseDto.class);
    }

    public PedidoResponseDto updatePedido(Integer id, PedidoRequestDto pedidoDto) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        modelMapper.map(pedidoDto, pedido);
        assert pedido != null;
        pedido = pedidoRepository.save(pedido);
        return modelMapper.map(pedido, PedidoResponseDto.class);
    }

    public void deletePedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
