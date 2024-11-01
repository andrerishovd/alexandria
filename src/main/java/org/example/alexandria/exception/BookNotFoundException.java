package org.example.alexandria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found")
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(int id) {
        super("No book found with id " + id);
    }
}
