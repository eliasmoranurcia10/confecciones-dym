package com.confeccionesdym.confecciones_dym.repository;

import com.confeccionesdym.confecciones_dym.model.entity.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Integer> {
    List<Prenda> findAllByColegioPrendaContainingIgnoreCase(String colegioPrenda);
    List<Prenda> findTop3ByOrderByCantidadStockAsc();
    List<Prenda> findTop3ByTipoPrendaContainingIgnoreCaseOrderByCantidadStockAsc(String tipoPrenda);
}
