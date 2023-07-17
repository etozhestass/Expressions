package expression.operation;

import expression.Expression;
import expression.operation.evaluator.Evaluator;

public class Mod<T> extends AbstractBinary<T> {

    public Mod(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "mod", true, false);
    }

    @Override
    protected int getType() {
        return 1;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.mod(x, y);
    }
}
