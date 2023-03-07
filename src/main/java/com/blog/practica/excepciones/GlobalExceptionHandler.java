package com.blog.practica.excepciones;

import com.blog.practica.DTO.DetallesDeErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetallesDeErrorDTO> manejarResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest){
        DetallesDeErrorDTO detallesDeError =
                new DetallesDeErrorDTO(
                        new Date(),
                        exception.getMessage(),
                        webRequest.getDescription(false));
        return new ResponseEntity<>(detallesDeError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String nombreDelCampo = ((FieldError) error).getField();
                    String mensage = error.getDefaultMessage();
                    errores.put(nombreDelCampo,mensage);
                });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }

}
