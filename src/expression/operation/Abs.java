package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Abs<T> extends AbstractUnary<T> {
    private final Expression<T> value;

    public Abs(Expression<T> value) {
        super(value, "abs");
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.abs(value.evaluate(x, y, z, evaluator));
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.abs(value.evaluate(x, evaluator));
    }

}
