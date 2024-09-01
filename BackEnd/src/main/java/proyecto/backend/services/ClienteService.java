package proyecto.backend.services;

import proyecto.backend.dtos.ClienteRequestDto;
import proyecto.backend.dtos.ClienteResponseDto;
import proyecto.backend.models.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;
    public ClienteService(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClienteResponseDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDto.class)).toList();
    }

    public ClienteResponseDto getClienteById(Integer id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return modelMapper.map(cliente, ClienteResponseDto.class);
    }

    public ClienteResponseDto saveCliente(ClienteRequestDto clienteRequestDto) {
        Cliente cliente = modelMapper.map(clienteRequestDto, Cliente.class);
        Cliente savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteResponseDto.class);
    }

    public ClienteResponseDto updateCliente(Integer id, ClienteRequestDto clienteRequestDto) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        modelMapper.map(clienteRequestDto, cliente);
        assert cliente != null;
        Cliente savedCliente = clienteRepository.save(cliente);
        return modelMapper.map(savedCliente, ClienteResponseDto.class);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
