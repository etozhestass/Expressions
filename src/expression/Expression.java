package expression;

import expression.operation.evaluator.Evaluator;

public interface
Expression<T> extends ToMiniString {
    T evaluate(T x, Evaluator<T> evaluator);
    T evaluate(T x, T y, T z, Evaluator<T> evaluator);
}

