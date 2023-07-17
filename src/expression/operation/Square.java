package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Square<T> extends AbstractUnary<T> {

    private final Expression<T> value;

    public Square(Expression<T> value) {
        super(value, "square");
        this.value = value;
    }


    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.square(value.evaluate(x, evaluator));
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.square(value.evaluate(x, y, z, evaluator));
    }
}
