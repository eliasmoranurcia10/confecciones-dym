package com.confeccionesdym.confecciones_dym.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "confeccion")
public class Confeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_confeccion")
    private Integer idConfeccion;

    @Column(name = "descripcion_confeccion")
    private String descripcionConfeccion;

    @Column(name = "estado_confeccion")
    private String estadoConfeccion;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name = "img_confeccion")
    private String imgConfeccion;

    @Column(name = "prenda_id_prenda")
    private Integer prendaIdPrenda;

    @ManyToOne
    @JoinColumn(name = "prenda_id_prenda", insertable = false, updatable = false)
    private Prenda prendaEntity;
}
