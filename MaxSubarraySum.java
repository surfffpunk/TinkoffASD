import java.util.*;
import java.io.*;

public class MaxSubarraySum {
    public static void main(String[] args) throws IOException {
        long[] array = readInput();
        long maxSum = calculateMaxSubarraySum(array);
        System.out.println(maxSum);
    }

    private static long[] readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        long[] array = new long[size];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            array[i] = Long.parseLong(input[i]);
        }
        return array;
    }

    private static long calculateMaxSubarraySum(long[] array) {
        int size = array.length;
        long[] prefixSum = calculatePrefixSum(array, size);
        return findMaxSubarraySum(array, prefixSum, size);
    }

    private static long[] calculatePrefixSum(long[] array, int size) {
        long[] prefixSum = new long[size+1];
        for (int i = 1; i <= size; i++) {
            prefixSum[i] = prefixSum[i-1] + array[i-1];
        }
        return prefixSum;
    }

    private static long findMaxSubarraySum(long[] array, long[] prefixSum, int size) {
        Stack<Integer> stack = new Stack<>();
        long maxSum = 0;
        for (int i = 0; i <= size; i++) {
            while (!stack.isEmpty() && (i == size || array[stack.peek()] > array[i])) {
                long height = array[stack.pop()];
                int j = stack.isEmpty() ? -1 : stack.peek();
                maxSum = Math.max(maxSum, height * (prefixSum[i] - prefixSum[j+1]));
            }
            stack.push(i);
        }
        return maxSum;
    }
}
