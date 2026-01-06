package com.confeccionesdym.confecciones_dym.model.entity;

import com.confeccionesdym.confecciones_dym.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prenda")
@EntityListeners(AuditingEntityListener.class)
public class Prenda extends AuditableEntity {

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

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "img_prenda")
    private String imgPrenda;

    @Column(name = "cantidad_stock", nullable = false)
    private Integer cantidadStock;

    @OneToMany(mappedBy = "prenda")
    private List<Venta> ventas;

    @OneToMany(mappedBy = "prenda")
    private List<Confeccion> confecciones;
}
