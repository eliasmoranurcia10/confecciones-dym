package com.confeccionesdym.confecciones_dym.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prenda")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenda")
    private Integer idPrenda;

    @Column(name = "tipo_prenda", nullable = false, length = 45)
    private String tipoPrenda;

    @Column(length = 3)
    private String talla;

    @Column(name = "colegio_prenda", length = 45)
    private String colegioPrenda;

    @Column(name = "img_prenda")
    private String imgPrenda;

    @Column(name = "cantidad_stock", nullable = false)
    private Integer cantidadStock;
}
