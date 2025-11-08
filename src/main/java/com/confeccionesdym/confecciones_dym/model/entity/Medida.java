package com.confeccionesdym.confecciones_dym.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medida")
public class Medida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medida")
    private Integer idMedida;

    @Column(precision = 10, scale = 2 )
    private BigDecimal espalda;

    @Column(precision = 10, scale = 2 )
    private BigDecimal busto;

    @Column(precision = 10, scale = 2 )
    private BigDecimal cintura;

    @Column(name = "talle_delantero", precision = 10, scale = 2 )
    private BigDecimal talleDelantero;

    @Column(name = "talle_espalda", precision = 10, scale = 2 )
    private BigDecimal talleEspalda;

    @Column(name = "contorno_busto", precision = 10, scale = 2 )
    private BigDecimal contornoBusto;

    @Column(name = "contorno_cintura", precision = 10, scale = 2 )
    private BigDecimal contornoCintura;

    @Column(name = "talle_corto", precision = 10, scale = 2 )
    private BigDecimal talleCorto;

    @Column(name = "alto_busto", precision = 10, scale = 2 )
    private BigDecimal altoBusto;

    @Column(name = "separacion_busto", precision = 10, scale = 2 )
    private BigDecimal separacionBusto;

    @Column(precision = 10, scale = 2 )
    private BigDecimal escote;

    @Column(name = "largo_manga", precision = 10, scale = 2 )
    private BigDecimal largoManga;

    @Column(precision = 10, scale = 2 )
    private BigDecimal punio;

    @Column(name = "largo_falda", precision = 10, scale = 2 )
    private BigDecimal largoFalda;

    @Column(name = "otros_detalles")
    private String otrosDetalles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_confeccion", referencedColumnName = "id_confeccion", insertable = false, updatable = false)
    private Confeccion confeccion;
}
