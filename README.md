## Expressions

### [Parser](src/expression/parser) and [evaluator](src/expression/operation/evaluator) expressions on Java with Generics and [Exceptions](src/expression/exceptions) based on course by [Georgiy Korneev](https://www.kgeorgiy.info/).

Realized [constants](src/expression/operation/Const.java), [variables](src/expression/operation/Variable.java), [add](src/expression/operation/Add.java),
[subtract](src/expression/operation/Subtract.java), [multiply](src/expression/operation/Multiply.java), [divide](src/expression/operation/Divide.java),
[absolute](src/expression/operation/Abs.java), [clear bits](src/expression/operation/Clear.java), [set bits](src/expression/operation/Set.java),
[module](src/expression/operation/Mod.java), [pow](src/expression/operation/Pow.java), [square](src/expression/operation/Square.java),
[unary minus](src/expression/operation/Negate.java), [log](src/expression/operation/Log.java), [count bits](src/expression/operation/Count.java).

### Possible to create expressions as:

```java
Expression<Integer> expression = new Subtract<>(
    new Multiply<>(
        new Const<>(2),
        new Variable<>("x")
    ),
    new Const<>(3)
);
```
### This is equals: ```2x - 3```. Method ```evaluate``` return value this expression.
```java
Evaluator<Integer> evaluator = new IntegerEvaluator(true); // true - exceptions
expression.evaluate(5, evaluator); // return 7
```
### Also you can use three variables:
```java
new Subtract<>(
    new Multiply<>(
        new Variable<>("x"),
        new Variable<>("y")
    ),
    new Multiply<>("z")
).evaluate(2, 3, 4, evaluator) // return 2
```

### toString and toMiniString:
toString return string of expression in full bracket form:
```java
new Subtract<>(
    new Multiply<>(
        new Const<>(2),
        new Variable<>("x")
    ),
    new Const<>(3)
).toString() // return ((2 * x) - 3)
```
toMiniString return string of expression with minimal number of parentheses:
```java
new Subtract<>(
    new Multiply<>(
        new Const<>(2),
        new Variable<>("x")
    ),
    new Const<>(3)
).toMiniString() // return 2 * x - 3
```

### Method equals. It returns equals of expressions:
```java
new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Const(2), new Variable("x"))); // return true

new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Variable("x"), new Const(2))); // return false
```

### [Evaluators](src/expression/operation/evaluator). This gives opportunity to evaluate in BigInteger, Double, Integer, Long, Short:
```java
Scanner in = new Scanner(System.in);
// evalation in Integer
Evaluator<Integer> evaluator = new IntegerEvaluator(true);
ExpressionParser<Integer> parser = new ExpressionParser<>(evaluator);
Expression<Integer> expression = parser.parse(in.nextLine());
// evalation in Long
//Evaluator<Integer> evaluator = new IntegerEvaluator(true);
//ExpressionParser<Integer> parser = new ExpressionParser<>(evaluator);
//Expression<Integer> expression = parser.parse(in.nextLine());
int x = in.nextInt();
int y = in.nextInt();
int z = in.nextInt();
System.out.println(expression.toMiniString() + " = " + expression.evaluate(x, y, z, evaluator));
in.close();
```

### Method parse. It parses string of expression and return expression:
```java
Evaluator<Integer> evaluator = new IntegerEvaluator(true);
ExpressionParser<Integer> parser = new ExpressionParser<>(evaluator);
Expression<Integer> expression = parser.parse("x * (x - 2)*x + 1");
int x = 1;

System.out.println(expression.toMiniString() + " = " + expression.evaluate(x, evaluator)); // x * (x - 2) * x + 1 = 0 
```
Time Complexity: ```O(n)```
### [Parser](src/expression/parser) also catches errors: overflow, no correct bracket sequences, divide by zero and etc:
```java
parser.parse("x + 2)"); // returns BracketSequencesException error
```