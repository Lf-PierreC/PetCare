package com.fatecmogidascruzes.petcare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fatecmogidascruzes.petcare.responses.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound (ResourceNotFoundException exception)
    {
        ApiError error = new ApiError(404, "Não encontrado", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidation (ValidationException exception)
    {
        ApiError error = new ApiError();
        error.setCode(422);
        error.setError("Erro de validação");
        error.setMessage("Há erros de validação nos campos enviados");
        error.setErrors(exception.getErrors());

        return ResponseEntity.unprocessableEntity().body(error);
    }
}
