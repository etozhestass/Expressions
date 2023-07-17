package expression.operation;

import expression.operation.evaluator.Evaluator;

import java.util.Objects;

public class Const<T> extends AbstractValue<T> {
    private final String value;

    public Const(T value) {
        super(value.toString());
        this.value = value.toString();
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.parse(value);
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return this.evaluate(x, evaluator);
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public int hashCode() {
        return Objects.hash(value) * 179;
    }


    @Override
    public boolean equals(Object obj) {
        return equalsImpl(obj) && ((Const<?>) obj).value.equals(this.value);
    }
}
