package com.confeccionesdym.confecciones_dym.controller.advice;

import com.confeccionesdym.confecciones_dym.dto.errordetail.ErrorDetailDto;
import com.confeccionesdym.confecciones_dym.exception.BadRequestException;
import com.confeccionesdym.confecciones_dym.exception.DuplicateResourceException;
import com.confeccionesdym.confecciones_dym.exception.InternalServerErrorException;
import com.confeccionesdym.confecciones_dym.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailDto> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse("not-found", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetailDto> handleBadRequest(BadRequestException ex) {
        return buildResponse("bad-request", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDetailDto> handleDataIntegrityViolation(DuplicateResourceException ex) {
        return buildResponse("data-integrity-violation", ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorDetailDto> handleInternalServer(InternalServerErrorException ex) {
        return buildResponse("internal-server", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailDto> handleGeneral(Exception ex) {
        return buildResponse("general-exception", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDetailDto>> handleException(MethodArgumentNotValidException ex) {
        List<ErrorDetailDto> errores = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errores.add(
                // Se puede agregar un new ErrorDetailDto() como opción
                buildResponse(
                        error.getField(),
                        error.getDefaultMessage(),
                        HttpStatus.BAD_REQUEST
                ).getBody()
        ));
        return ResponseEntity.badRequest().body(errores);
    }

    private ResponseEntity<ErrorDetailDto> buildResponse(String type, String message, HttpStatus status) {
        ErrorDetailDto errorDetailDto = new ErrorDetailDto(type, message, LocalDateTime.now().format(FORMATTER));
        return ResponseEntity.status(status).body(errorDetailDto);
    }
}
