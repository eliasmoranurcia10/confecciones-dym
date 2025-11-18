package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import com.confeccionesdym.confecciones_dym.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Prueba de gestión de entidades con clientes para verificar qué prendas realizaron")
@AllArgsConstructor
public class ClientesController {

    private final ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        List<Cliente> clientesResult = new ArrayList<>();

        for (Cliente c: clientes) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(c.getIdCliente());
            cliente.setNombresCliente(c.getNombresCliente());
            cliente.setApellidosCliente(c.getApellidosCliente());
            cliente.setCelularCliente(c.getCelularCliente());
            clientesResult.add(cliente);
        }
        return ResponseEntity.ok(clientesResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        return ResponseEntity.ok(this.clienteRepository.findById(id).orElse(null));
    }
}
