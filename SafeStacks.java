import java.util.Scanner;

public class SafeStacks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        int result = countSafeStacks(n);
        System.out.println(result);
        scanner.close();
    }

    public static int countSafeStacks(int n) {
        int[][] safeStacks = new int[n + 1][3];

        for (int j = 0; j < 3; j++) {
            safeStacks[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j != k || (j == k && j != 0)) {
                        safeStacks[i][j] += safeStacks[i - 1][k];
                    }
                }
            }
        }

        int totalSafeStacks = 0;
        for (int j = 0; j < 3; j++) {
            totalSafeStacks += safeStacks[n][j];
        }
        return totalSafeStacks;
    }
}
