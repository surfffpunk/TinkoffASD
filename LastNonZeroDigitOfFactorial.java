import java.util.Scanner;

public class LastNonZeroDigitOfFactorial {
    private static final int[] LAST_NON_ZERO_DIGIT = {1, 1, 2, 6, 4, 2, 2, 4, 2, 8};

    public static void main(String[] args) {
        int number = getInput();
        System.out.println(lastNonZeroDigitOfFactorial(number));
    }

    private static int getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int lastNonZeroDigitOfFactorial(int number) {
        if (number < 10) {
            return LAST_NON_ZERO_DIGIT[number];
        } else {
            int lastNonZeroDigit = ((number / 10) % 10 % 2 == 0) ? 6 : 4;
            lastNonZeroDigit *= lastNonZeroDigitOfFactorial(number / 5);
            lastNonZeroDigit *= LAST_NON_ZERO_DIGIT[number % 10];
            return lastNonZeroDigit % 10;
        }
    }
}
