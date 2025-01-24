package com.proyectoFInal.bazar.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> HandlerMethodArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> HandlerMethodArgumentType(MethodArgumentTypeMismatchException exception){
        Map<String, String> errors = new HashMap<>();

        String errorMessage = "El valor proporcionado para el parámetro '" + exception.getName() + "' no es válido. "
                + "Se esperaba un tipo de dato '" + exception.getRequiredType().getSimpleName() + "'.";

        errors.put("Error", errorMessage);
        return errors;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> HandlerMethodArgumentType(HttpMessageNotReadableException exception){
        Map<String, String> errors = new HashMap<>();


        errors.put("El cuerpo de la solicitud es inválido: " , exception.getMessage());
        return errors;
    }

}
