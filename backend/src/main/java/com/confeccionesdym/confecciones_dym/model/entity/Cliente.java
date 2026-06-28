package com.confeccionesdym.confecciones_dym.model.entity;

import com.confeccionesdym.confecciones_dym.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombres_cliente", nullable = false, length = 45)
    private String nombresCliente;

    @Column(name = "apellidos_cliente", nullable = false, length = 45)
    private String apellidosCliente;

    @Column(name = "celular_cliente", nullable = false, length = 9, unique = true)
    private String celularCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

    @OneToMany(mappedBy = "cliente")
    private List<Medida> medidas;
}
