import java.util.Arrays;
import java.util.Scanner;

public class Cows {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfStalls = scanner.nextInt();
        int numberOfCows = scanner.nextInt();

        long[] stalls = new long[numberOfStalls];
        for (int i = 0; i < numberOfStalls; ++i)
            stalls[i] = scanner.nextLong();

        Arrays.sort(stalls);

        System.out.println(calculateMaxDistance(stalls, numberOfCows));
    }

      static boolean canPlaceCows(long[] stalls, int cows, long minDistance) {
        long lastCowPosition = stalls[0];
        int placedCows = 1;

        for (int i = 1; i < stalls.length; ++i) {
            if (stalls[i] - lastCowPosition >= minDistance) {
                lastCowPosition = stalls[i];
                placedCows++;
                if (placedCows == cows)
                    return true;
            }
        }

        return false;
    }

    static long calculateMaxDistance(long[] stalls, int cows) {
        long minDistance = 0;
        long maxDistance = stalls[stalls.length - 1] - stalls[0];

        long result = 0;

        while (minDistance <= maxDistance) {
            long midDistance = minDistance + (maxDistance - minDistance) / 2;

            if (canPlaceCows(stalls, cows, midDistance)) {
                result = midDistance;
                minDistance = midDistance + 1;
            }
            else {
                maxDistance = midDistance - 1;
            }
        }

        return result;
    }
}
