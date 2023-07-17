package expression.exceptions;

public class OverflowException extends ExpressionException {

    public OverflowException(String type) {
        this.message = "Overflow(" + type + ")";
    }
}
