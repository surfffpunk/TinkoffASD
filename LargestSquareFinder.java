import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LargestSquareFinder {
    private static final FastScanner scanner = new FastScanner();

    public static void main(String[] args) throws IOException {
        int[][] matrix = readMatrix();
        int[] largestSquare = findLargestSquare(matrix);
        printLargestSquare(largestSquare);
    }

    private static int[][] readMatrix() throws IOException {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[] findLargestSquare(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        int max = 0, maxX = 0, maxY = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]) + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        maxX = i;
                        maxY = j;
                    }
                }
            }
        }
        return new int[]{max, maxX + 1, maxY + 1};
    }

    private static void printLargestSquare(int[] largestSquare) {
        System.out.println(largestSquare[0]);
        System.out.println(largestSquare[1] + " " + largestSquare[2]);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
