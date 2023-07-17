package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Log<T> extends AbstractUnary<T> {

    private final Expression<T> value;

    public Log(Expression<T> value) {
        super(value, "log10");
        this.value = value;
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.log(value.evaluate(x, evaluator));
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.log(value.evaluate(x, y, z, evaluator));
    }
}
