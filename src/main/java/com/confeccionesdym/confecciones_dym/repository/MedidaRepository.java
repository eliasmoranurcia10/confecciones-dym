package com.confeccionesdym.confecciones_dym.repository;

import com.confeccionesdym.confecciones_dym.model.entity.Medida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Integer> {
}
