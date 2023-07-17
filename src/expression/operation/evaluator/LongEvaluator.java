package expression.operation.evaluator;

public class LongEvaluator implements Evaluator<Long> {
    @Override
    public Long add(Long left, Long right) {
        return left + right;
    }

    @Override
    public Long subtract(Long left, Long right) {
        return left - right;
    }

    @Override
    public Long multiply(Long left, Long right) {
        return left * right;
    }

    @Override
    public Long divide(Long left, Long right) {
        return left / right;
    }

    @Override
    public Long negate(Long digit) {
        return -digit;
    }

    @Override
    public Long abs(Long digit) {
        return Math.abs(digit);
    }

    @Override
    public Long mod(Long left, Long right) {
        return left % right;
    }

    @Override
    public Long square(Long digit) {
        return digit * digit;
    }

    @Override
    public Long parse(String expression) {
        return Long.parseLong(expression);
    }

    @Override
    public Long clear(Long left, Long right) {
        return left & ~(1L << right);
    }

    @Override
    public Long set(Long left, Long right) {
        return left | (1L << right);
    }

    @Override
    public Long pow(Long digit) {
        long res = 1;
        for (long i = 0; i < digit; i++) {
            res *= 10;
        }
        return res;
    }

    @Override
    public Long count(Long digit) {
        return (long) Long.bitCount(digit);
    }

    @Override
    public Long log(Long digit) {
        long count = 0;
        while (digit >= 10) {
            digit /= 10;
            count++;
        }
        return count;
    }

    @Override
    public Long intToType(int digit) {
        return (long) digit;
    }
}
