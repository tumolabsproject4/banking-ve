package edu.tumo.banking.exception;

public class NotFoundValueException extends RuntimeException {
    public NotFoundValueException(String s) {

        super("Value not found");
    }

    public NotFoundValueException(Throwable e) {

        super("Value not found", e);
    }
}
