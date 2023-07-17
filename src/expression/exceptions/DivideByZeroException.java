package expression.exceptions;

public class DivideByZeroException extends ExpressionException {
    public DivideByZeroException() {
        this.message = "Division by zero";
    }
}
