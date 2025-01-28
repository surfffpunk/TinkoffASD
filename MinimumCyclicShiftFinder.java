import java.util.Scanner;

public class MinimumCyclicShiftFinder {

    public static int findMinimumRotation(String s) {
        String doubleS = s + s;
        int n = doubleS.length();
        int i = 0, j = 1, k = 0;
        while (i + k < n && j + k < n) {
            if (doubleS.charAt(i + k) == doubleS.charAt(j + k)) {
                k++;
            } else if (doubleS.charAt(i + k) > doubleS.charAt(j + k)) {
                i += k + 1;
                if (i <= j) i = j + 1;
                k = 0;
            } else {
                j += k + 1;
                if (j <= i) j = i + 1;
                k = 0;
            }
        }
        return Math.min(i, j);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int minRotationIndex = findMinimumRotation(s);

        System.out.println(s.substring(minRotationIndex) + s.substring(0, minRotationIndex));
    }
}
