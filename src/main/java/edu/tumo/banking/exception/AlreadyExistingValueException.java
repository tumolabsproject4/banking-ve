package edu.tumo.banking.exception;

public class AlreadyExistingValueException extends RuntimeException {

    public AlreadyExistingValueException(String s) {
        super(" The value already exists");
    }

    public AlreadyExistingValueException(Throwable e) {
        super("The value already exists", e);
    }
}
