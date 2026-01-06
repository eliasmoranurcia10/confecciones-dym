package com.confeccionesdym.confecciones_dym.model.entity;

import com.confeccionesdym.confecciones_dym.model.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venta")
@EntityListeners(AuditingEntityListener.class)
public class Venta extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @Column(name = "tipo_pago", nullable = false, length = 45)
    private String tipoPago;

    @Column(name = "cantidad_venta", nullable = false)
    private Integer cantidadVenta;

    @Column(name = "total_pago", nullable = false, precision = 10, scale = 2 )
    private BigDecimal totalPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenda", referencedColumnName = "id_prenda")
    private Prenda prenda;
}
