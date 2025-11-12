package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.measure.MeasureRequestDto;
import com.confeccionesdym.confecciones_dym.dto.measure.MeasureResponseDto;
import com.confeccionesdym.confecciones_dym.service.MedidaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measures")
@Tag(name = "Medidas", description = "Gestión de Medidas")
@AllArgsConstructor
public class MedidaController {

    private final MedidaService medidaService;

    @GetMapping
    public ResponseEntity<List<MeasureResponseDto>> getAllMeasures() {
        return ResponseEntity.ok(this.medidaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureResponseDto> getMeasureById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.medidaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MeasureResponseDto> saveMeasure(@RequestBody @Valid MeasureRequestDto measureRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.medidaService.save(measureRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasureResponseDto> updateMeasure(
            @PathVariable Integer id,
            @RequestBody @Valid MeasureRequestDto measureRequestDto
    ) {
        return ResponseEntity.ok(this.medidaService.update(id, measureRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeasure(@PathVariable Integer id) {
        this.medidaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
