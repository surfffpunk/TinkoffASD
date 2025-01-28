import java.util.Scanner;

public class PrimeFactorization {
    public static void main(String[] args) {
        int number = getInput();
        printPrimeFactors(number);
    }

    private static int getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void printPrimeFactors(int number) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i * i <= number; i++) {
            int count = 0;
            while (number % i == 0) {
                count++;
                number /= i;
            }
            if (count > 1) {
                result.append(i).append("^").append(count).append("*");
            } else if (count == 1) {
                result.append(i).append("*");
            }
        }
        if (number > 1) {
            result.append(number);
        } else {
            result.setLength(result.length() - 1);
        }
        System.out.println(result.toString());
    }
}
