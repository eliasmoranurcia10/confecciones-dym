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
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "dni_usuario", nullable = false, length = 8, unique = true)
    private String dniUsuario;

    @Column(name = "nombres_usuario", nullable = false, length = 45)
    private String nombresUsuario;

    @Column(name = "apellidos_usuario", nullable = false, length = 45)
    private String apellidosUsuario;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "rol_usuario", nullable = false, length = 45)
    private String rolUsuario;

    @Column(name = "celular_usuario", nullable = false, length = 9, unique = true)
    private String celularUsuario;

    @Column(name = "locked", nullable = false, columnDefinition = "TINYINT")
    private Boolean locked;

    @Column(name = "disabled", nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;
}
