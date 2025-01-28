import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BalancedBrackets {
    static int[][] minOperations;
    static int[][] splitPosition;
    static String input;
    static StringBuilder result = new StringBuilder();

    static void constructResult(int left, int right) {
        if (minOperations[left][right] == right - left + 1) return;
        if (minOperations[left][right] == 0) {
            result.append(input.substring(left, right + 1)); // corrected here
            return;
        }
        if (splitPosition[left][right] == -10) {
            result.append(input.charAt(left));
            constructResult(left + 1, right - 1);
            result.append(input.charAt(right));
            return;
        }
        constructResult(left, splitPosition[left][right]);
        constructResult(splitPosition[left][right] + 1, right);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
        int length = input.length();
        minOperations = new int[length][length];
        splitPosition = new int[length][length];

        if (length > 0) {
            for (int i = 0; i < length; i++) {
                Arrays.fill(minOperations[i], 0, i, 0);
                minOperations[i][i] = 1;
            }
            for (int right = 0; right < length; right++) {
                for (int left = right; left >= 0; left--) {
                    if (left == right) {
                        minOperations[left][right] = 1;
                    } else {
                        int min = Integer.MAX_VALUE;
                        int minSplit = -10;
                        char leftChar = input.charAt(left);
                        char rightChar = input.charAt(right);
                        if ((leftChar == '(' && rightChar == ')') || (leftChar == '[' && rightChar == ']') || (leftChar == '{' && rightChar == '}')) {
                            min = minOperations[left + 1][right - 1];
                        }
                        for (int k = left; k < right; k++) {
                            int current = minOperations[left][k] + minOperations[k + 1][right];
                            if (min > current) {
                                min = current;
                                minSplit = k;
                            }
                        }
                        minOperations[left][right] = min;
                        splitPosition[left][right] = minSplit;
                    }
                }
            }

            constructResult(0, length - 1);
            System.out.println(result.toString());
        } else {
            System.out.println("");
        }
    }
}
