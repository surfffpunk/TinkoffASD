import java.util.Scanner;

public class ArraySeparation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        long[] array = new long[n];
        long sum = 0;
        long maxElement = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextLong();
            sum += array[i];
            maxElement = Math.max(maxElement, array[i]);
        }

        long result = findMinimumMaxSum(array, maxElement, sum, k);
        System.out.println(result);
    }

    public static long findMinimumMaxSum(long[] array, long left, long right, int k) {
        long result = -1;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isValidPartition(array, mid, k)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static boolean isValidPartition(long[] array, long maxSum, int k) {
        int segments = 1;
        long currentSum = 0;
        for (long num : array) {
            currentSum += num;
            if (currentSum > maxSum) {
                segments++;
                currentSum = num;
            }
        }
        return segments <= k;
    }
}
