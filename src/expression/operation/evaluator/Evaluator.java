package expression.operation.evaluator;

public interface Evaluator<T> {
    T add(T left, T right);
    T subtract(T left, T right);
    T multiply(T left, T right);
    T divide(T left, T right);
    T negate(T digit);
    T abs(T digit);
    T mod(T left, T right);
    T square(T digit);
    T parse(String toString);
    T clear(T left, T right);
    T set(T left, T right);
    T pow(T digit);
    T count(T digit);
    T log(T digit);
    T intToType(int digit);
}
