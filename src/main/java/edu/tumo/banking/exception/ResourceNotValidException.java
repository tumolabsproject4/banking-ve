package edu.tumo.banking.exception;

public class ResourceNotValidException extends RuntimeException {
    public ResourceNotValidException(String message) {
        super("Resource is not valid");
    }

    public ResourceNotValidException(Throwable e) {

        super("Resource is not valid", e);
    }

}
