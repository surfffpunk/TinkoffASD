import java.util.*;

public class Grasshopper {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] nk = readInput();
        int n = nk[0];
        int k = nk[1];
        int[] coins = readCoins(n);
        int[] dp = new int[n];
        calculateMaxCoins(n, k, coins, dp);
        printResult(n, dp, coins, k);
    }

    private static int[] readInput() {
        return new int[]{scanner.nextInt(), scanner.nextInt()};
    }

    private static int[] readCoins(int n) {
        int[] coins = new int[n];
        for (int i = 1; i < n - 1; i++) {
            coins[i] = scanner.nextInt();
        }
        return coins;
    }

    private static void calculateMaxCoins(int n, int k, int[] coins, int[] dp) {
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= i + k && j < n; j++) {
                dp[j] = Math.max(dp[j], dp[i] + coins[j]);
            }
        }
    }

    private static void printResult(int n, int[] dp, int[] coins, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(dp[n - 1]).append("\n");
        Deque<Integer> jumps = calculateJumps(n, dp, coins, k);
        sb.append(jumps.size() - 1).append("\n");
        while (!jumps.isEmpty()) {
            sb.append(jumps.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static Deque<Integer> calculateJumps(int n, int[] dp, int[] coins, int k) {
        Deque<Integer> jumps = new ArrayDeque<>();
        int i = n - 1;
        while (i > 0) {
            jumps.push(i + 1);
            for (int j = 1; j <= k; j++) {
                if (dp[i] == dp[i - j] + coins[i]) {
                    i -= j;
                    break;
                }
            }
        }
        jumps.push(1);
        return jumps;
    }
}
