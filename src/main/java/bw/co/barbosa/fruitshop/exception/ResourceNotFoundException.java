package bw.co.barbosa.fruitshop.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String msg) { super(msg); }

    public ResourceNotFoundException(String msg, Throwable cause) { super(msg, cause); }

    public ResourceNotFoundException(Throwable cause) { super(cause); }

    public ResourceNotFoundException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
