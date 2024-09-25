package proyecto.backend.services;

import jakarta.persistence.EntityNotFoundException;
import proyecto.backend.dtos.UsuarioRequestDto;
import proyecto.backend.dtos.UsuarioResponseDto;
import proyecto.backend.models.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<UsuarioResponseDto> findAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario->modelMapper.map(usuario, UsuarioResponseDto.class)).toList();
    }

    public UsuarioResponseDto findUserById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public UsuarioResponseDto saveUser(UsuarioRequestDto usuarioDto) {
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        Usuario savedUser = usuarioRepository.save(usuario);
        return modelMapper.map(savedUser, UsuarioResponseDto.class);
    }

    public UsuarioResponseDto updateUser(UsuarioRequestDto usuarioDto, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        modelMapper.map(usuarioDto, usuario);
        assert usuario != null;
        Usuario savedUser = usuarioRepository.save(usuario);
        return modelMapper.map(savedUser, UsuarioResponseDto.class);
    }

    public void softDelete(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Usuario user = usuario.get();
            user.setActive(false);
            usuarioRepository.save(user);
        } else {
            throw new EntityNotFoundException("Producto no encontrado con id: " + id);
        }
    }
}
