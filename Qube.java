import java.util.Scanner;

public class Qube {
    private static double a, b, c, d;

    private static double calculateFunctionValue(double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    private static double findInitialGuess() {
        double guess = 1;

        while (calculateFunctionValue(guess) * calculateFunctionValue(-guess) >= 0) {
            guess *= 2;
        }

        return guess;
    }

    private static double findRoot() {
        double epsilon = 1e-7;
        double left = -findInitialGuess();
        double right = findInitialGuess();

        while (right - left > epsilon) {
            double middle = (left + right) / 2;
            if (calculateFunctionValue(middle) * calculateFunctionValue(right) > 0) {
                right = middle;
            } else {
                left = middle;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        a = scanner.nextDouble();
        b = scanner.nextDouble();
        c = scanner.nextDouble();
        d = scanner.nextDouble();

        double root = findRoot();

        System.out.println(root);
    }
}
