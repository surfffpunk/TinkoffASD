import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LISFinder{
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int n = reader.nextInt();
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = reader.nextInt();
        }
        System.out.println(longestIncreasingSubsequence(sequence));
    }

    public static String longestIncreasingSubsequence(int[] sequence) {
        int n = sequence.length;
        int[] dp = new int[n];
        int[] prev = new int[n];

        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        StringBuilder subsequence = new StringBuilder();
        for (int i = maxIndex; i != -1; i = prev[i]) {
            subsequence.insert(0, sequence[i] + " ");
        }

        return dp[maxIndex] + "\n" + subsequence.toString().trim();
    }
}
