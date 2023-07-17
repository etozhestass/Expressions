package expression.operation.evaluator;

import expression.exceptions.NegateNumberLog;
import expression.exceptions.OverflowException;

public class IntegerEvaluator implements Evaluator<Integer> {
    private final boolean check;

    public IntegerEvaluator(boolean check) {
        this.check = check;
    }

    @Override
    public Integer add(Integer left, Integer right) {
        checkAddOverflow(left, right);
        return left + right;
    }

    private void checkAddOverflow(int left, int right) {
        if (check && ((right > 0 && Integer.MAX_VALUE - right < left) || (right < 0 && Integer.MIN_VALUE - right > left))) {
            throw new OverflowException("Addition");
        }
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        checkSubtractOverflow(left, right);
        return left - right;
    }

    private void checkSubtractOverflow(int left, int right) {
        if (check && ((right < 0 && Integer.MAX_VALUE + right < left) || (right > 0 && Integer.MIN_VALUE + right > left))) {
            throw new OverflowException("Subtract");
        }
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        checkMultiplyOverflow(left, right);
        return left * right;
    }

    protected void checkMultiplyOverflow(int left, int right) {
        int res = left * right;
        if (check && (left != 0 && right != 0 && (res / left != right || res / right != left))) {
            throw new OverflowException("Multiply");
        }
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        checkDivideOverflow(left, right);
        return left / right;
    }

    protected void checkDivideOverflow(int left, int right) {
        if (check && (left == Integer.MIN_VALUE && right == -1)) {
            throw new OverflowException("Divide");
        }
    }

    @Override
    public Integer negate(Integer digit) {
        checkNegateAndAbsOverflow(digit);
        return -digit;
    }

    @Override
    public Integer abs(Integer digit) {
        checkNegateAndAbsOverflow(digit);
        return Math.abs(digit);
    }

    @Override
    public Integer mod(Integer left, Integer right) {
        return left % right;
    }

    @Override
    public Integer square(Integer digit) {
        checkSquareOverflow(digit);
        return digit * digit;
    }

    protected void checkSquareOverflow(int digit) {
        if (check && (Math.abs(digit) >= Math.sqrt(Integer.MAX_VALUE) || digit == Integer.MIN_VALUE)) {
            throw new OverflowException("Square");
        }
    }

    protected void checkNegateAndAbsOverflow(int digit) {
        if (check && digit == Integer.MIN_VALUE) {
            throw new OverflowException("UnaryMin or Abs");
        }
    }

    @Override
    public Integer parse(String digit) {
        return Integer.parseInt(digit);
    }

    @Override
    public Integer clear(Integer left, Integer right) {
        return Math.toIntExact(new LongEvaluator().clear(left.longValue(), right.longValue()));
    }

    @Override
    public Integer set(Integer left, Integer right) {
        return Math.toIntExact(new LongEvaluator().set(left.longValue(), right.longValue()));
    }

    @Override
    public Integer pow(Integer digit) {
        if (check) {
            if (digit < 0) {
                throw new NegateNumberLog("The number less than zero: " + digit);
            } else if (digit > 9) {
                throw new OverflowException("Pow: " + digit);
            }
        }
        return Math.toIntExact(new LongEvaluator().pow(digit.longValue()));
    }

    @Override
    public Integer count(Integer digit) {
        return Integer.bitCount(digit);
    }

    @Override
    public Integer log(Integer digit) {
        if (check && digit <= 0) {
            throw new NegateNumberLog("The number less than zero: " + digit);
        }
        return Math.toIntExact(new LongEvaluator().log(digit.longValue()));
    }

    @Override
    public Integer intToType(int digit) {
        return digit;
    }

}
