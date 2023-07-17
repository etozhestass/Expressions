package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Add<T> extends AbstractBinary<T> {
    public Add(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "+", false, false);
    }

    @Override
    protected int getType() {
        return 1;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.add(x, y);
    }
}

