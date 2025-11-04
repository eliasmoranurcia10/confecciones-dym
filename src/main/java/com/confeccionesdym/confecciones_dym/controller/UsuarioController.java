package com.confeccionesdym.confecciones_dym.controller;

import com.confeccionesdym.confecciones_dym.dto.user.UserRequestDto;
import com.confeccionesdym.confecciones_dym.dto.user.UserResponseDto;
import com.confeccionesdym.confecciones_dym.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Gestión de usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(this.usuarioService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.save(userRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Integer id,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        return ResponseEntity.ok(this.usuarioService.update(id, userRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
