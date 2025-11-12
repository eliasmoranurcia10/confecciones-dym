package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionRequestDto;
import com.confeccionesdym.confecciones_dym.dto.confection.ConfectionResponseDto;
import com.confeccionesdym.confecciones_dym.service.ConfeccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/confections")
@Tag(name = "Confecciones", description = "Gestión de confecciones")
@AllArgsConstructor
public class ConfeccionController {

    private final ConfeccionService confeccionService;

    @GetMapping
    public ResponseEntity<List<ConfectionResponseDto>> getAllConfections() {
        return ResponseEntity.ok(this.confeccionService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfectionResponseDto> getConfectionById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.confeccionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ConfectionResponseDto> saveConfection(@RequestBody @Valid ConfectionRequestDto confectionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.confeccionService.save(confectionRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfectionResponseDto> updateConfection(
            @PathVariable Integer id,
            @RequestBody @Valid ConfectionRequestDto confectionRequestDto
    ) {
        return ResponseEntity.ok(this.confeccionService.update(id, confectionRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfection(@PathVariable Integer id) {
        this.confeccionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
