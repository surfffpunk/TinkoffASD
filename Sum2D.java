import java.util.Scanner;

public class Sum2D {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        long[][] matrix = new long[n + 1][m + 1];
        long[][] prefix_sum = new long[n + 1][m + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                matrix[i][j] = scanner.nextLong();
                prefix_sum[i][j] = prefix_sum[i - 1][j] + prefix_sum[i][j - 1] - prefix_sum[i - 1][j - 1] + matrix[i][j];
            }
        }

        while (k-- > 0) {
            int y1 = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y2 = scanner.nextInt();
            int x2 = scanner.nextInt();
            long sum = prefix_sum[y2][x2] - prefix_sum[y1 - 1][x2] - prefix_sum[y2][x1 - 1] + prefix_sum[y1 - 1][x1 - 1];
            System.out.println(sum);
        }

        scanner.close();
    }
}
