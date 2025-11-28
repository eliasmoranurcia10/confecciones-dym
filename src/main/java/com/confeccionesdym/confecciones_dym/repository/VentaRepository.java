package com.confeccionesdym.confecciones_dym.repository;

import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findAllByTipoPagoOrderByTotalPagoDesc(String tipoPago);
}
