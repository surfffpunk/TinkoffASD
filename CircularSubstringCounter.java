import java.util.Scanner;

public class CircularSubstringCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        scanner.close();

        int cyclicShiftCount = countCyclicShifts(a, b);
        System.out.println(cyclicShiftCount);
    }

    private static int countCyclicShifts(String a, String b) {
        String doubleA = a + a;
        int[] prefixFunction = computePrefixFunction(b + "#" + doubleA);
        int count = 0;
        for (int i = 2*b.length(); i < prefixFunction.length; i++) {
            if (prefixFunction[i] == b.length()) {
                count++;
            }
        }
        return count;
    }

    private static int[] computePrefixFunction(String s) {
        int[] pi = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }
}
