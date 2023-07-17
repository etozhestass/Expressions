package expression.exceptions;

public class ExpressionException extends RuntimeException{
    String message;

    @Override
    public String getMessage() {
        return message;
    }
}
