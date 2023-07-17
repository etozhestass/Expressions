package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Multiply<T> extends AbstractBinary<T> {
    public Multiply(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "*", false, true);
    }


    @Override
    protected int getType() {
        return 2;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.multiply(x, y);
    }
}

