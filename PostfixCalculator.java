import java.util.Scanner;
import java.util.Stack;

public class PostfixCalculator {
    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator();
        calculator.run();
    }

    private void run() {
        String expression = getInput();
        int result = calculateExpression(expression);
        System.out.println(result);
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private int calculateExpression(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (isOperator(token)) {
                performOperation(stack, token);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    private void performOperation(Stack<Integer> stack, String operator) {
        int operand2 = stack.pop();
        int operand1 = stack.pop();
        int result;

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException(operator);
        }
        stack.push(result);
    }
}
