package expression.operation;

import expression.Expression;
import expression.exceptions.NoCorrectType;
import expression.operation.evaluator.Evaluator;

public class Clear<T> extends AbstractBinary<T> {
    public Clear(Expression<T> leftPart, Expression<T> rightPart) {
        super(leftPart, rightPart, "clear", true, false);
    }

    @Override
    protected int getType() {
        return 0;
    }

    @Override
    protected T calculate(T x, T y, Evaluator<T> evaluator) {
        return evaluator.clear(x, y);
    }
}
