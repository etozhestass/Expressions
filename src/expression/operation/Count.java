package expression.operation;

import expression.Expression;
import expression.exceptions.NoCorrectType;
import expression.operation.evaluator.Evaluator;

public class Count<T> extends AbstractUnary<T> {

    private final Expression<T> value;

    public Count(Expression<T> value) {
        super(value, "count");
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.count(value.evaluate(x, y, z, evaluator));
    }


    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.count(value.evaluate(x, evaluator));
    }
}
