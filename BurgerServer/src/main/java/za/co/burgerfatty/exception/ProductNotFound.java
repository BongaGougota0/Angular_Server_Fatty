package za.co.burgerfatty.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String exceptionMessage){
        super(exceptionMessage);
    }
}
