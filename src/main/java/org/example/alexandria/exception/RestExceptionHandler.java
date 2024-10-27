package org.example.alexandria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException() {
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .errorMessage("Book not found")
                .statusCode(404)
                .timeStamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND
        );
    }
}
