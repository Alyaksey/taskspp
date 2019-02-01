package sample.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionParser {
    private final String[] FUNCTIONS = {"cos", "sin", "tan", "cot", "pow", "sqrt"};
    private final String OPERATORS = "+-*/";
    private final String SEPARATOR = ",";
    private final String DELIMS = "+-*/,()";
    private Stack<String> stackOperators;
    private Stack<String> stackRPN;
    private Stack<String> stackAnswer;

    public ExpressionParser() {
        this.stackOperators = new Stack<>();
        this.stackRPN = new Stack<>();
        this.stackAnswer = new Stack<>();
    }

    private boolean isNumber(String token) {
        try {
            new BigDecimal(token);
        } catch (NumberFormatException e) {
            try {
                new BigInteger(token, 16);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    private boolean isFunction(String token) {
        for (String item : FUNCTIONS) {
            if (item.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSeparator(String token) {
        return token.equals(SEPARATOR);
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    public String convertExpression(String expression, int fromSystem, int toSystem) {
        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                DELIMS, true);
        StringBuilder convertedExpression = new StringBuilder("");
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isNumber(token))
                convertedExpression.append(new BigInteger(token, fromSystem).toString(toSystem));
            else
                convertedExpression.append(token);
        }
        return convertedExpression.toString().toUpperCase();
    }

    private void parseExpression(StringBuilder expression) {
        stackOperators.clear();
        stackRPN.clear();
        if (expression.indexOf("(-") != -1)
            expression.insert(expression.indexOf("(-") + 1, "0");
        if (expression.indexOf(",-") != -1)
            expression.insert(expression.indexOf("(-") + 1, "0");
        if (expression.charAt(0) == '-')
            expression.insert(0, "0");
        StringTokenizer stringTokenizer = new StringTokenizer(expression.toString(),
                DELIMS, true);
        String token;
        while (stringTokenizer.hasMoreTokens()) {
            token = stringTokenizer.nextToken();
            if (isSeparator(token)) {
                while (!stackOperators.empty()
                        && !isOpenBracket(stackOperators.lastElement())) {
                    stackRPN.push(stackOperators.pop());
                }
            } else if (isOpenBracket(token)) {
                stackOperators.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperators.empty()
                        && !isOpenBracket(stackOperators.lastElement())) {
                    stackRPN.push(stackOperators.pop());
                }
                stackOperators.pop();
                if (!stackOperators.empty()
                        && isFunction(stackOperators.lastElement())) {
                    stackRPN.push(stackOperators.pop());
                }
            } else if (isNumber(token)) {
                stackRPN.push(token);
            } else if (isOperator(token)) {
                while (!stackOperators.empty()
                        && isOperator(stackOperators.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperators
                        .lastElement())) {
                    stackRPN.push(stackOperators.pop());
                }
                stackOperators.push(token);
            } else if (isFunction(token)) {
                stackOperators.push(token);
            }
        }
        while (!stackOperators.empty()) {
            stackRPN.push(stackOperators.pop());
        }
        Collections.reverse(stackRPN);
    }

    public String calcExpression(StringBuilder expression) {
        parseExpression(expression);
        String token;
        while (!stackRPN.empty()) {
            token = stackRPN.pop();
            if (isNumber(token))
                stackAnswer.push(token);
            else if (isOperator(token)) {
                switch (token) {
                    case "+":
                        stackAnswer.push(new BigDecimal(stackAnswer.pop())
                                .add(new BigDecimal(stackAnswer.pop()))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "-":
                        BigDecimal secondSub = new BigDecimal(stackAnswer.pop());
                        BigDecimal firstSub = new BigDecimal(stackAnswer.pop());
                        stackAnswer.push(firstSub.subtract(secondSub)
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "*":
                        stackAnswer.push(new BigDecimal(stackAnswer.pop())
                                .multiply(new BigDecimal(stackAnswer.pop()))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "/":
                        BigDecimal secondDiv = new BigDecimal(stackAnswer.pop());
                        BigDecimal firstDiv = new BigDecimal(stackAnswer.pop());
                        stackAnswer.push(firstDiv.divide(secondDiv, 16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                }
            } else if (isFunction(token)) {
                switch (token) {
                    case "sin":
                        stackAnswer.push(new BigDecimal(Math.sin(Double.parseDouble(stackAnswer.pop())))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "cos":
                        stackAnswer.push(new BigDecimal(Math.cos(Double.parseDouble(stackAnswer.pop())))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "tan":
                        stackAnswer.push(new BigDecimal(Math.tan(Double.parseDouble(stackAnswer.pop())))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "cot":
                        stackAnswer.push(new BigDecimal("1")
                                .divide(new BigDecimal(Math.tan(Double.parseDouble(stackAnswer.pop()))), 16, BigDecimal.ROUND_HALF_EVEN)
                                .stripTrailingZeros()
                                .toPlainString());
                        break;
                    case "pow":
                        String power = stackAnswer.pop();
                        String base = stackAnswer.pop();
                        stackAnswer.push(new BigDecimal(Math.pow(Double.parseDouble(base), Double.parseDouble(power)))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .toPlainString());
                        break;
                    case "sqrt":
                        stackAnswer.push(new BigDecimal(Math.sqrt(Double.parseDouble(stackAnswer.pop())))
                                .setScale(16, BigDecimal.ROUND_HALF_EVEN)
                                .toPlainString());
                        break;
                }
            }
        }
        return stackAnswer.pop();
    }

    public String calcExpression(StringBuilder expression, int numeralSystem) {
        parseExpression(expression);
        String token;
        while (!stackRPN.empty()) {
            token = stackRPN.pop();
            if (isNumber(token))
                stackAnswer.push(token);
            else if (isOperator(token)) {
                switch (token) {
                    case "+":
                        stackAnswer.push(new BigInteger(stackAnswer.pop(), numeralSystem)
                                .add(new BigInteger(stackAnswer.pop(), numeralSystem))
                                .toString(numeralSystem)
                                .toUpperCase());
                        break;
                    case "-":
                        BigInteger secondSub = new BigInteger(stackAnswer.pop(), numeralSystem);
                        BigInteger firstSub = new BigInteger(stackAnswer.pop(), numeralSystem);
                        stackAnswer.push(firstSub.subtract(secondSub).toString(numeralSystem).toUpperCase());
                        break;
                    case "*":
                        stackAnswer.push(new BigInteger(stackAnswer.pop(), numeralSystem)
                                .multiply(new BigInteger(stackAnswer.pop(), numeralSystem))
                                .toString(numeralSystem))
                                .toUpperCase();
                        break;
                    case "/":
                        BigInteger secondDiv = new BigInteger(stackAnswer.pop(), numeralSystem);
                        BigInteger firstDiv = new BigInteger(stackAnswer.pop(), numeralSystem);
                        stackAnswer.push(firstDiv.divide(secondDiv).toString(numeralSystem).toUpperCase());
                        break;
                }
            }
        }
        return stackAnswer.pop();
    }
}
