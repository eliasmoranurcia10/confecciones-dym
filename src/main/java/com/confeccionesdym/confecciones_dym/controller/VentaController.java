package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.sale.SaleRequestDto;
import com.confeccionesdym.confecciones_dym.dto.sale.SaleResponseDto;
import com.confeccionesdym.confecciones_dym.service.VentaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Tag(name = "Ventas", description = "Gestión de ventas")
@AllArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<SaleResponseDto>> getAllSales() {
        return ResponseEntity.ok(this.ventaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> getSaleById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.ventaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaleResponseDto> saveSale(@RequestBody @Valid SaleRequestDto saleRequestDto) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.ventaService.save(saleRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDto> updateSale(
            @PathVariable Integer id,
            @RequestBody @Valid SaleRequestDto saleRequestDto
    ) {
        return ResponseEntity.ok(this.ventaService.update(id, saleRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Integer id) {
        this.ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
