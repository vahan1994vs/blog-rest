package com.vahan.blok.rest.exception;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelNotFoundException(Throwable cause) {
        super(cause);
    }

    public ModelNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
