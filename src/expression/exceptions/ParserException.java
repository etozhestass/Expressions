package expression.exceptions;

public class ParserException extends RuntimeException {
    String message;

    @Override
    public String getMessage() {
        return message;
    }
}
