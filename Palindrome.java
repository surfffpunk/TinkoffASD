import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        scanner.nextLine();
        String input = scanner.nextLine();

        String palindrome = findPalindrome(input);

        System.out.println(palindrome);
    }

    static String findPalindrome(String input) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        StringBuilder firstHalf = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            if (charCount.containsKey(c)) {
                int count = charCount.get(c);

                if (count % 2 == 0) {
                    appendCharacters(firstHalf, c, count / 2);
                } else {
                    if (middle.length() == 0) {
                        middle.append(c);
                    }
                    appendCharacters(firstHalf, c, (count - 1) / 2);
                }
            }
        }

        StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();

        return firstHalf.toString() + middle.toString() + secondHalf.toString();
    }

    private static void appendCharacters(StringBuilder sb, char c, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
    }
}
