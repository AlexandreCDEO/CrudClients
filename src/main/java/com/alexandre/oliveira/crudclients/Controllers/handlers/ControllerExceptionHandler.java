package com.alexandre.oliveira.crudclients.Controllers.handlers;

import com.alexandre.oliveira.crudclients.dto.CustomErrorDTO;
import com.alexandre.oliveira.crudclients.dto.FieldMessageDTO;
import com.alexandre.oliveira.crudclients.dto.ValidationErrorDTO;
import com.alexandre.oliveira.crudclients.exceptions.ClientNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> clientNotFoundExceptionHandler(
            ClientNotFoundException exception,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new CustomErrorDTO(Instant.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ){
        var validationErrorDTO = new ValidationErrorDTO(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Dados inválidos", request.getRequestURI());

        for(FieldError erro: exception.getBindingResult().getFieldErrors()){
            validationErrorDTO.addErro(new FieldMessageDTO(erro.getField(), erro.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationErrorDTO);
    }
}
