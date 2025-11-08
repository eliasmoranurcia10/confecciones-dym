package com.confeccionesdym.confecciones_dym.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "confeccion")
public class Confeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_confeccion")
    private Integer idConfeccion;

    @Column(name = "descripcion_confeccion")
    private String descripcionConfeccion;

    @Column(name = "estado_confeccion", length = 45, nullable = false)
    private String estadoConfeccion;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @Column(name = "img_confeccion")
    private String imgConfeccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda", referencedColumnName = "id_prenda", updatable = false, insertable = false)
    private Prenda prenda;

    @OneToMany(mappedBy = "confeccion")
    private List<Medida> medidas;

}
