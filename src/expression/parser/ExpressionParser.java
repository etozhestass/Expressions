package expression.parser;

import expression.exceptions.BracketSequencesException;
import expression.exceptions.EmptyExpressionException;
import expression.exceptions.NoTokenException;
import expression.exceptions.UnexpectedTokenException;
import expression.Expression;
import expression.operation.evaluator.Evaluator;
import expression.operation.*;

import java.util.List;

public class ExpressionParser<T> extends BaseParser<T> {
    private final List<String> stringsBinary = List.of("+", "-", "*", "/", "mod", "set", "clear");
    private final List<String> stringsUnary = List.of("abs", "square", "count", "log10", "pow10");
    private final List<Character> stringOperationPrefix = List.of('c', 'l', 'p', 's', 'a', 'm', '+', '-', '*', '/');

    private int countBracket = 0;

    public ExpressionParser(Evaluator<T> evaluator) {
        super(evaluator);
    }

    public Expression<T> parse(final String expression) {
        setSource(new StringSource(expression));
        countBracket = 0;
        skipWhitespace();
        if (expression.isEmpty() || !hasNext()) {
            throw new EmptyExpressionException();
        }
        Expression<T> result = parse(1);
        skipWhitespace();
        if (hasNext() && top() == ')') {
            countBracket--;
            next();
            skipWhitespace();
        }
        if (hasNext()) {
            throw new UnexpectedTokenException(getToken(stringsBinary, stringsBinary), getData(), getPos() - 1);
        }
        if (countBracket != 0) {
            throw new BracketSequencesException((countBracket > 0 ? "opening" : "closing") + " brackets: " + expression);
        }
        return result;
    }
    private Expression<T> parse(int priority) {
        if (getBinaryOperation(priority).isEmpty()) {
            return element();
        }
        skipWhitespace();
        Expression<T> result = parse(priority + 1);
        if (hasNext() && top() == '(') {
            throw new BracketSequencesException("closing Brackets: ", getData(), getPos());
        }
        if (hasNext() && isNotSupportedSymbol(top())) {
            throw new UnexpectedTokenException(String.valueOf(top()), getData(), getPos() + 1);
        }
        while (hasNext() && getBinaryOperation(priority).contains(top())) {
            skipWhitespace();
            String operation = getToken(stringsBinary, stringsUnary);
            if (!stringsBinary.contains(operation) || operation.length() > 1 && !hasNext() && !isWhitespace()) {
                throw new UnexpectedTokenException(
                        operation + getToken(stringsBinary, stringsUnary),
                        getData(),
                        getPos()
                );
            }
            result = getBinaryExpression(operation, result, parse(priority + 1));
        }
        return result;
    }
    private Expression<T> element() {
        skipWhitespace();
        if (!hasNext()) {
            throw new NoTokenException(getData());
        }
        if (isNotSupportedSymbol(top())) {
            throw new UnexpectedTokenException(String.valueOf(top()), getData(), getPos());
        }
        if (equals('(')) {
            countBracket++;
            next();
            if (equals(')')) {
                throw new EmptyExpressionException(getData(), getPos() - 1);
            }
            Expression<T> result = parse(1);
            skipWhitespace();
            if (!hasNext()) {
                throw new BracketSequencesException("closing brackets: ", getData(), getPos());
            }
            if (isNotSupportedSymbol(top())) {
                throw new UnexpectedTokenException(String.valueOf(top()), getData(), getPos());
            }
            if (equals(')')) {
                countBracket--;
                skipWhitespace();
                next();
            }
            return result;
        } else if (equals('-')) {
            next();
            if (!hasNext()) {
                throw new UnexpectedTokenException("-", getData(), getPos());
            }
            if (!isWhitespace() && between(top(), '1', '9')) {
                return new Const<>(parseNumber(true));
            } else {
                return new Negate<>(element());
            }
        } else if (between(top(), '0', '9')) {
            return new Const<>(parseNumber(false));
        } else if (between(top(), 'x', 'z')) {
            return new Variable<>(String.valueOf(next()));
        } else {
            String operation = String.valueOf(next());
            if (!stringOperationPrefix.contains(operation.charAt(0))) {
                throw new UnexpectedTokenException(operation, getData(), getPos());
            }
            if (!stringsBinary.contains(operation) && !stringsUnary.contains(operation)) {
                operation += getToken(stringsBinary, stringsUnary);
            }
            if (stringsUnary.contains(operation)) {
                if (hasNext() && !isWhitespace() && !equals('(')) {
                    throw new UnexpectedTokenException(operation + top(), getData(), getPos() + 1);
                }
                return getUnaryExpression(operation, element());
            }
            throw new UnexpectedTokenException(operation, getData(), getPos() - operation.length());
        }
    }
    private Expression<T> getBinaryExpression(String operator, Expression<T> result, Expression<T> parse) {
        return switch (operator) {
            case "+" -> new Add<>(result, parse);
            case "-" -> new Subtract<>(result, parse);
            case "*" -> new Multiply<>(result, parse);
            case "/" -> new Divide<>(result, parse);
            case "mod" -> new Mod<>(result, parse);
            case "clear" -> new Clear<>(result, parse);
            case "set" -> new Set<>(result, parse);
            default -> throw error("Impossible");
        };
    }
    private Expression<T> getUnaryExpression(String operation, Expression<T> element) {
        return switch (operation) {
            case "abs" -> new Abs<>(element);
            case "square" -> new Square<>(element);
            case "log10" -> new Log<>(element);
            case "count" -> new Count<>(element);
            case "pow" -> new Pow<>(element);
            default -> throw error("Impossible");
        };
    }
    private List<Character> getBinaryOperation(int priority) {
        return switch (priority) {
            case 1 -> List.of('m', 's', 'c');
            case 2 -> List.of('+', '-');
            case 3 -> List.of('*', '/');
            default -> List.of();
        };
    }
    private boolean isNotSupportedSymbol(char ch) {
        return !between(ch, '0', '9') && !between(ch, 'x', 'z') &&
                ch != '(' && ch != ')' && !stringOperationPrefix.contains(ch);
    }
}
