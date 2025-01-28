import java.util.Scanner;
import java.util.Arrays;

public class MaxPalindrome {
    static int[][] palindromeLength;
    static char[] inputString;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        inputString = s.toCharArray();
        int length = s.length();
        palindromeLength = new int[length][length];
        for (int[] row : palindromeLength) {
            Arrays.fill(row, -1);
        }
        int maxLength = findMaxLength(0, length - 1);
        System.out.println(maxLength);
        System.out.println(reconstructPalindrome(0, length - 1, maxLength));
    }

    static int findMaxLength(int start, int end) {
        if (start >= end) {
            return (start == end) ? 1 : 0;
        }
        if (palindromeLength[start][end] != -1) {
            return palindromeLength[start][end];
        }
        if (inputString[start] == inputString[end]) {
            palindromeLength[start][end] = findMaxLength(start + 1, end - 1) + 2;
        } else {
            palindromeLength[start][end] = Math.max(findMaxLength(start + 1, end), findMaxLength(start, end - 1));
        }
        return palindromeLength[start][end];
    }

    static String reconstructPalindrome(int start, int end, int len) {
        if (len == 1 || len == 0) {
            return String.valueOf(inputString[start]);
        }
        if (inputString[start] == inputString[end]) {
            return inputString[start] + reconstructPalindrome(start + 1, end - 1, len - 2) + inputString[end];
        } else if (palindromeLength[start + 1][end] > palindromeLength[start][end - 1]) {
            return reconstructPalindrome(start + 1, end, len);
        } else {
            return reconstructPalindrome(start, end - 1, len);
        }
    }
}
