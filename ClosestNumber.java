import java.util.Scanner;

public class ClosestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] sortedArray = readArray(scanner, n);
        int[] queries = readArray(scanner, k);

        for (int query : queries) {
            int closestNumber = findClosestNumber(sortedArray, query);
            System.out.println(closestNumber);
        }
    }

    private static int[] readArray(Scanner scanner, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static int findClosestNumber(int[] sortedArray, int target) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (sortedArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int closestIndex = left - 1;
        return (closestIndex >= 0 && isCloserToLeft(sortedArray, closestIndex, target))
                ? sortedArray[closestIndex]
                : sortedArray[left];
    }

    private static boolean isCloserToLeft(int[] sortedArray, int index, int target) {
        int distanceToLeft = Math.abs(sortedArray[index] - target);
        int distanceToCurrent = Math.abs(sortedArray[index + 1] - target);
        return distanceToLeft <= distanceToCurrent;
    }
}
