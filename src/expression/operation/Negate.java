package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Negate<T> extends AbstractUnary<T> {
    private final Expression<T> value;

    public Negate(Expression<T> value) {
        super(value, "-");
        this.value = value;
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.negate(value.evaluate(x, evaluator));
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.negate(value.evaluate(x, y, z, evaluator));
    }
    @Override
    public boolean equals(Object obj) {
        return equalsImpl(obj) && ((Negate<?>) obj).value.equals(this.value);
    }
}
