package edu.tumo.banking.exception;

public class NotFoundValue extends RuntimeException{
    public NotFoundValue(String message) {
        super(message);
    }
    public NotFoundValue(String message,Throwable e) {
        super(message,e);
    }
}
