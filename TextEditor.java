import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextEditor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();
        int numOfQueries = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numOfQueries; i++) {
            String query = reader.readLine();
            searchAndPrintOccurrences(text, query);
        }
    }

    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    private static void searchAndPrintOccurrences(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int textLength = text.length();
        int patternLength = pattern.length();
        int i = 0;
        int j = 0;
        int count = 0;
        StringBuilder result = new StringBuilder();
        while (i < textLength) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == patternLength) {
                count++;
                result.append(i - j).append(" ");
                j = lps[j - 1];
            } else if (i < textLength && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        System.out.println(count + " " + result.toString().trim());
    }
}
