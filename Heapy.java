import java.util.*;

public class Heapy {
    static int[] heap;
    static int size;

    static void insert(int x) {
        int i = size;
        heap[i] = x;
        while (i > 0 && heap[(i - 1) / 2] < heap[i]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
        size++;
    }

    static int extract() {
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return max;
    }

    static void heapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    static void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        heap = new int[n];
        size = 0;
        for (int i = 0; i < n; i++) {
            int command = scanner.nextInt();
            if (command == 0) {
                int x = scanner.nextInt();
                insert(x);
            } else if (command == 1) {
                System.out.println(extract());
            }
        }
    }
}
