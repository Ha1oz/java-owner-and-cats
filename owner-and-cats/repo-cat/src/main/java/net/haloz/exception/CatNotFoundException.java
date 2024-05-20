package net.haloz.exception;

public class CatNotFoundException extends RuntimeException {
    public CatNotFoundException() {
    }

    public CatNotFoundException(String message) {
        super(message);
    }

    public CatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
