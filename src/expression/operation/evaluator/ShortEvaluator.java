package expression.operation.evaluator;

public class ShortEvaluator implements Evaluator<Short> {
    @Override
    public Short add(Short left, Short right) {
        return (short) (left + right);
    }

    @Override
    public Short subtract(Short left, Short right) {
        return (short) (left - right);
    }

    @Override
    public Short multiply(Short left, Short right) {
        return (short) (left * right);
    }

    @Override
    public Short divide(Short left, Short right) {
        return (short) (left / right);
    }

    @Override
    public Short negate(Short digit) {
        return (short) (-digit);
    }

    @Override
    public Short abs(Short digit) {
        return (short) Math.abs(digit);
    }

    @Override
    public Short mod(Short left, Short right) {
        return (short) (left % right);
    }

    @Override
    public Short square(Short digit) {
        return (short) (digit * digit);
    }

    @Override
    public Short parse(String expression) {
        return Short.parseShort(expression);
    }

    @Override
    public Short clear(Short left, Short right) {
        return Short.valueOf(String.valueOf(new LongEvaluator().clear(left.longValue(), right.longValue())));
    }

    @Override
    public Short set(Short left, Short right) {
        return Short.valueOf(String.valueOf(new LongEvaluator().set(left.longValue(), right.longValue())));
    }

    @Override
    public Short pow(Short digit) {
        return Short.valueOf(String.valueOf(new LongEvaluator().pow(digit.longValue())));
    }

    @Override
    public Short count(Short digit) {
        return Short.valueOf(String.valueOf(new LongEvaluator().count(digit.longValue())));
    }

    @Override
    public Short log(Short digit) {
        return Short.valueOf(String.valueOf(new LongEvaluator().log(digit.longValue())));
    }

    @Override
    public Short intToType(int digit) {
        return (short) digit;
    }
}
