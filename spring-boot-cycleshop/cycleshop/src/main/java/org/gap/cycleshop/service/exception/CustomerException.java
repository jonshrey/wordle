package org.gap.cycleshop.service.exception;


public class CustomerException extends RuntimeException{
    public CustomerException(String message) {
        super(message);
    }    

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}
