package proyecto.backend.repositories;

import proyecto.backend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByActiveIsTrue();
    Usuario findByActiveIsTrueAndIdUsuario(Integer idUsuario);
}
