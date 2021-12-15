package edu.tumo.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")  // 404
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {super("Resource not found"); }

    public ResourceNotFoundException(Throwable e) {
        super("Resource not found", e);
    }
}
