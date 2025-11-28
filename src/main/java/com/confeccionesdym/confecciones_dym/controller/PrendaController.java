package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.garment.GarmentRequestDto;
import com.confeccionesdym.confecciones_dym.dto.garment.GarmentResponseDto;
import com.confeccionesdym.confecciones_dym.service.PrendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garments")
@Tag(name = "Prendas", description = "Gestión de Prendas")
@AllArgsConstructor
public class PrendaController {

    private final PrendaService prendaService;

    @GetMapping
    public ResponseEntity<List<GarmentResponseDto>> getAllGarments() {
        return ResponseEntity.ok(this.prendaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarmentResponseDto> getGarmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.prendaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GarmentResponseDto> saveGarment(@RequestBody @Valid GarmentRequestDto garmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.prendaService.save(garmentRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarmentResponseDto> updateGarment(
            @PathVariable Integer id,
            @RequestBody @Valid GarmentRequestDto garmentRequestDto
    ) {
        return ResponseEntity.ok(this.prendaService.update(id, garmentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarment(@PathVariable Integer id){
        this.prendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
