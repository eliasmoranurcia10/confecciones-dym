package com.confeccionesdym.confecciones_dym.repository;

import com.confeccionesdym.confecciones_dym.model.entity.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Integer> {
}
