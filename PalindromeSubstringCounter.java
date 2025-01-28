import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromeSubstringCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        System.out.println(countPalindromeSubstrings(input));
    }

    public static int countPalindromeSubstrings(String str) {
        StringBuilder modifiedString = new StringBuilder("$#");
        for (char ch : str.toCharArray()) {
            modifiedString.append(ch).append('#');
        }
        int n = modifiedString.length();
        int[] palindromeLength = new int[n];

        int center = 0, rightEdge = 0, totalCount = 0;
        for (int i = 1; i < n; i++) {
            if (i < rightEdge) {
                palindromeLength[i] = Math.min(rightEdge - i, palindromeLength[2 * center - i]);
            }
            while (modifiedString.charAt(i + palindromeLength[i] + 1) == modifiedString.charAt(i - palindromeLength[i] - 1)) {
                palindromeLength[i]++;
            }
            totalCount += (palindromeLength[i] + 1) / 2;
            if (i + palindromeLength[i] > rightEdge) {
                center = i;
                rightEdge = i + palindromeLength[i];
            }
        }

        return totalCount;
    }
}
