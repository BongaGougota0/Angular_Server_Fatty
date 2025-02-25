package za.co.burgerfatty.exception;

public class AddressNotFound extends RuntimeException {
    public AddressNotFound(String message) {
        super(message);
    }
}
