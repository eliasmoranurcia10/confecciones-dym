package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.client.ClientRequestDto;
import com.confeccionesdym.confecciones_dym.dto.client.ClientResponseDto;
import com.confeccionesdym.confecciones_dym.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clientes", description = "Gestión de clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        return ResponseEntity.ok(this.clienteService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> saveClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.save(clientRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(
            @PathVariable Integer id,
            @RequestBody @Valid ClientRequestDto clientRequestDto
    ) {
        return ResponseEntity.ok(this.clienteService.update(id, clientRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
