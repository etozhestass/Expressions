package expression.exceptions;

public class NoCorrectWhitespace extends ParserException{
    public NoCorrectWhitespace(String message) {
        this.message = message;
    }
}
