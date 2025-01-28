import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Merge{
    private static long inversions = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sorted = mergeSort(arr);

        System.out.println(inversions);
        printArray(sorted);
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);

        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] merged = new int[leftArr.length + rightArr.length];
        int leftIndex = 0, rightIndex = 0;

        for (int i = 0; i < merged.length; i++) {
            if (rightIndex == rightArr.length || (leftIndex < leftArr.length && leftArr[leftIndex] <= rightArr[rightIndex])) {
                merged[i] = leftArr[leftIndex++];
            } else {
                merged[i] = rightArr[rightIndex++];
                inversions += leftArr.length - leftIndex;
            }
        }

        return merged;
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
