package expression.operation;

import expression.Expression;
import expression.exceptions.NoCorrectType;
import expression.operation.evaluator.Evaluator;

public class Pow<T> extends AbstractUnary<T> {
    private final Expression<T> value;

    public Pow(Expression<T> value) {
        super(value, "pow10");
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z, Evaluator<T> evaluator) {
        return evaluator.pow(value.evaluate(x, y, z, evaluator));
    }

    @Override
    public T evaluate(T x, Evaluator<T> evaluator) {
        return evaluator.pow(value.evaluate(x, evaluator));
    }

    /*public T evaluateImple(T num) {
        if (num instanceof Integer) {
            Integer res = 1;
            for (int i = 0; i < (Integer) num; i++) {
                res *= 10;
            }
            return (T) res;
        } else if (num instanceof Long) {
            Long res = 1L;
            for (int i = 0; i < (Long) num; i++) {
                res *= 10;
            }
            return (T) res;
        }
        throw new NoCorrectType("No Long and no Integer");
    }*/
}
