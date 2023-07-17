package expression.exceptions;

public class NoTokenException extends ParserException{
    public NoTokenException(String message) {
        this.message = "Expected token: " + message;
    }
}
