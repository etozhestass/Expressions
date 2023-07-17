import expression.Expression;
import expression.operation.evaluator.Evaluator;
import expression.operation.evaluator.IntegerEvaluator;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Evaluator<Integer> evaluator = new IntegerEvaluator(true);
        ExpressionParser<Integer> parser = new ExpressionParser<>(evaluator);
        Expression<Integer> expression = parser.parse(in.nextLine());
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        System.out.println(expression.toMiniString() + " = " + expression.evaluate(x, y, z, evaluator));
        in.close();
    }
}
