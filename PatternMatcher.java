import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PatternMatcher {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        String text = reader.readLine();

        List<Integer> occurrences = findOccurrencesWithOneMismatch(pattern, text);

        printOccurrences(occurrences);
    }

    private static List<Integer> findOccurrencesWithOneMismatch(String pattern, String text) {
        List<Integer> occurrences = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();

        for (int i = 0; i <= textLength - patternLength; i++) {
            if (isOneMismatchWithinLimit(pattern, text.substring(i, i + patternLength))) {
                occurrences.add(i + 1);
            }
        }

        return occurrences;
    }

    private static boolean isOneMismatchWithinLimit(String pattern, String subText) {
        int mismatchCount = 0;

        for (int j = 0; j < pattern.length(); j++) {
            if (pattern.charAt(j) != subText.charAt(j)) {
                mismatchCount++;
                if (mismatchCount > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printOccurrences(List<Integer> occurrences) {
        System.out.println(occurrences.size());
        for (int occurrence : occurrences) {
            System.out.print(occurrence + " ");
        }
    }
}
