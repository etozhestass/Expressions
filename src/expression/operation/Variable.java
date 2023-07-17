package expression.operation;

import expression.exceptions.UnexpectedTokenException;
import expression.operation.evaluator.Evaluator;

public class Variable<T> extends AbstractValue<T> {
    private final String value;

    public Variable(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        if ("x".equals(value)) {
            return x;
        }
        throw new UnexpectedTokenException("No such variable");
    }


    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return switch(value) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new UnexpectedTokenException("No such variable");
        };
    }

    @Override
    public boolean equals(Object obj) {
        return equalsImpl(obj) && value.equals(((Variable<?>) obj).value);
    }
}
