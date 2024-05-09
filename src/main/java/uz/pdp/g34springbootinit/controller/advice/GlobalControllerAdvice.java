package uz.pdp.g34springbootinit.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.g34springbootinit.dto.web.ErrorResponseDTO;
import uz.pdp.g34springbootinit.exception.DataNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({DataNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleDataNotFoundException(DataNotFoundException e) {
        final var dto = new ErrorResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(dto);
    }
}
