package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Divide<T> extends AbstractBinary<T> {
    public Divide(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "/", true, true);
    }


    @Override
    protected int getType() {
        return 2;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.divide(x, y);
    }
}

