package expression.exceptions;

public class NoCorrectType extends ExpressionException{
    public NoCorrectType(String str) {
        this.message = str;
    }
}
