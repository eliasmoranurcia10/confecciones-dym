package com.confeccionesdym.confecciones_dym.repository;

import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findAllByNombresClienteContainingIgnoreCase(
            String nombresCliente
    );

    List<Cliente> findAllByApellidosClienteContainingIgnoreCase(
            String apellidosCliente
    );

    List<Cliente> findAllByNombresClienteContainingIgnoreCaseAndApellidosClienteContainingIgnoreCase(
            String nombresCliente,
            String apellidosCliente
    );
}
