package com.confeccionesdym.confecciones_dym.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

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
}
