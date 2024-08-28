package models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Rol")
    private String rol;

    @Column(name = "Contraseña")
    private String contrasena;
}
