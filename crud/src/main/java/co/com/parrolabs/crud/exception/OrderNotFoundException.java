package co.com.parrolabs.crud.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
