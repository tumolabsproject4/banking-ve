package edu.tumo.banking.exception;

public class ResourceNotValid extends RuntimeException{
    public ResourceNotValid(String message) {
        super(message);
    }
    public ResourceNotValid(String message,Throwable e) {
        super(message,e);
    }

}
