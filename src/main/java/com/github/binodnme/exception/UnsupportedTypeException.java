package com.github.binodnme.exception;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 11/11/17.
 */
public class UnsupportedTypeException extends RuntimeException{

    public UnsupportedTypeException() {
        super();
    }

    public UnsupportedTypeException(String message) {
        super(message);
    }
}
