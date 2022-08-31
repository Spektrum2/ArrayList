package exceptions;

public class IndexNotFoundException extends RuntimeException{
    public IndexNotFoundException(String message) {
        super(message);
    }
}
