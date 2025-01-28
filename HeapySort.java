import java.util.Scanner;

public class HeapySort {
    public static void main(String[] args) {
        int[] array = readInput();
        heapSort(array);
        printArray(array);
    }
    public static int[] readInput() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    public static void heapSort(int[] array) {
        int size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(array, size, i);

        for (int i = size - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void heapify(int[] array, int size, int i) {
        int max = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < size && array[leftChild] > array[max])
            max = leftChild;

        if (rightChild < size && array[rightChild] > array[max])
            max = rightChild;

        if (max != i) {
            swap(array, i, max);
            heapify(array, size, max);
        }
    }
}
