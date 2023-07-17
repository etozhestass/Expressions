package expression.operation;

//
//
// HARD VERISON
//
//

import expression.Expression;
import expression.operation.evaluator.Evaluator;

import java.util.Objects;

public abstract class AbstractBinary<T> implements Expression<T> {
    private final Expression<T> leftPart;
    private final Expression<T> rightPart;
    private final String operator;
    private final boolean priority;
    private final boolean isCommutative;

    protected abstract int getType();
    protected abstract T calculate(T x, T y, Evaluator<T> evaluator);

    public AbstractBinary(Expression<T> leftPart, Expression<T> rightPart, String operator, boolean priority, boolean isDistributive) {
        this.leftPart = leftPart;
        this.rightPart = rightPart;
        this.operator = operator;
        this.priority = priority;
        this.isCommutative = isDistributive;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return calculate(leftPart.evaluate(x, y, z, evaluator), rightPart.evaluate(x, y, z, evaluator), evaluator);
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return calculate(leftPart.evaluate(x, evaluator), rightPart.evaluate(x, evaluator), evaluator);
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", leftPart.toString(), operator, rightPart.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractBinary) {
            return obj.toString().equals(toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftPart, rightPart, operator, priority) * 179;
    }

    private boolean less(Expression<T> obj) {
        if (!(obj instanceof AbstractBinary<T> o)) {
            return false;
        }
        return o.getType() < this.getType();
    }

    @Override
    public String toMiniString() {
        return String.format("%s %s %s",
                toMiniString(leftPart, less(leftPart)),
                operator,
                toMiniString(rightPart, less(rightPart) || right(rightPart))
        );
    }

    private String toMiniString(Expression<T> obj, boolean check) {
        String parsed = "%s";
        if (check) {
            parsed = String.format("(%s)", parsed);
        }
        return String.format(parsed, obj.toMiniString());
    }

    private boolean right(Expression<T> obj) {
        if (!(obj instanceof AbstractBinary<T> o)) {
            return false;
        }
        if (priority && this.getType() >= o.getType()) {
            return true;
        }
        return o.priority && isCommutative;
    }
}

