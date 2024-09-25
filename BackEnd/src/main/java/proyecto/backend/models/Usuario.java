package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_usuario")
    private int idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "User_Name")
    private String userName;

    @Column(name = "Rol")
    private String rol;

    @Column(name = "Contraseña")
    private String contrasena;

    @Column(name = "is_active")
    private boolean active;
}
