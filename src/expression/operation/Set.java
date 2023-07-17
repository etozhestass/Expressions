package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Set<T> extends AbstractBinary<T> {
    public Set(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "set", true, false);
    }

    @Override
    protected int getType() {
        return 0;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.set(x, y);
    }
}
