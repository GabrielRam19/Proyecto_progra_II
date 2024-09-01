package proyecto.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "formapago")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_forma_pago")
    private int idFormaPago;

    @Column(name="Forma_pago")
    private String tipoFormaPago;
}
