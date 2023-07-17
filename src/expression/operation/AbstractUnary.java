package expression.operation;

import expression.Expression;

public abstract class AbstractUnary<T> implements Expression<T> {
    private final Expression<T> value;
    private final String operator;

    public AbstractUnary(Expression<T> value, String operator) {
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", operator, value.toString());
    }

    public boolean equalsImpl(Object obj) {
        return obj != null && obj.getClass() == getClass();
    }

    @Override
    public String toMiniString() {
        if (value instanceof AbstractBinary) {
            return operator + "(" + value.toMiniString() + ")";
        }
        return operator + ' ' + value.toMiniString();
    }
}
