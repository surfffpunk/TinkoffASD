import java.util.Scanner;

public class GoldbachConjecture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        printGoldbachPair(number);
    }

    public static void printGoldbachPair(int number) {
        for (int i = 2; i < number; i++) {
            if (isPrime(i) && isPrime(number - i)) {
                System.out.println(i + " " + (number - i));
                break;
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        int sqrtNum = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrtNum; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
