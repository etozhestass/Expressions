package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Subtract<T> extends AbstractBinary<T> {
    public Subtract(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "-", true, false);
    }


    @Override
    protected int getType() {
        return 1;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.subtract(x, y);
    }
}

