import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PackSymbols {
    static final int MAX_LEN = (int) 1e9;
    static int strLength;
    static String str;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        str = reader.readLine();
        strLength = str.length();
        dp = new int[strLength][strLength];
        for (int[] row : dp) {
            Arrays.fill(row, MAX_LEN);
        }
        solve();
    }

    static int countDigits(int num) {
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    static String generateAbbreviation(int start, int end) {
        if (start == end) {
            return String.valueOf(str.charAt(start));
        }
        int len = end - start + 1;
        for (int step = 1; step <= len / 2; step++) {
            if (len % step != 0) continue;
            boolean isOK = true;
            for (int cur = 0; cur < step; cur++) {
                for (int pos = start + cur; pos <= end; pos += step) {
                    if (str.charAt(start + cur) != str.charAt(pos)) {
                        isOK = false;
                        break;
                    }
                }
            }
            if (isOK) {
                int curLen = countDigits(len / step) + 2 + dp[start][start + step - 1];
                if (curLen == dp[start][end]) {
                    return (len / step) + "(" + generateAbbreviation(start, start + step - 1) + ")";
                }
            }
        }
        for (int m = start; m < end; m++) {
            if (dp[start][m] + dp[m + 1][end] == dp[start][end]) {
                return generateAbbreviation(start, m) + generateAbbreviation(m + 1, end);
            }
        }
        return str.substring(start, end + 1);
    }

    static void solve() {
        for (int len = 0; len < strLength; len++) {
            for (int i = 0; i < strLength; i++) {
                int j = i + len;
                if (j >= strLength) break;
                if (len == 0) {
                    dp[i][j] = 1;
                } else {
                    int lenSubStr = j - i + 1;
                    for (int step = 1; step <= lenSubStr / 2; step++) {
                        if (lenSubStr % step != 0) continue;
                        boolean isOK = true;
                        for (int cur = 0; cur < step; cur++) {
                            for (int pos = i + cur; pos <= j; pos += step) {
                                if (str.charAt(i + cur) != str.charAt(pos)) {
                                    isOK = false;
                                    break;
                                }
                            }
                        }
                        if (isOK) {
                            int curLen = countDigits(lenSubStr / step) + 2 + dp[i][i + step - 1];
                            dp[i][j] = Math.min(dp[i][j], curLen);
                        }
                    }
                    for (int m = i; m < j; m++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m + 1][j]);
                    }
                }
            }
        }
        System.out.println(generateAbbreviation(0, strLength - 1));
    }
}
