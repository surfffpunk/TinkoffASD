import java.util.*;
import java.io.*;

public class MinInWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] nk = parseInput(reader.readLine());
        int[] sequence = parseInput(reader.readLine());
        System.out.println(minOnInterval(nk[0], nk[1], sequence));
    }

    private static int[] parseInput(String input) {
        return Arrays.stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static String minOnInterval(int n, int k, int[] sequence) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[n - k + 1];

        for (int i = 0; i < k; i++) {
            removeGreaterElements(deque, sequence, i);
            deque.offerLast(i);
        }

        for (int i = k; i < n; i++) {
            result[i - k] = sequence[deque.peekFirst()];
            removeOutOfWindowElements(deque, i, k);
            removeGreaterElements(deque, sequence, i);
            deque.offerLast(i);
        }

        result[n - k] = sequence[deque.peekFirst()];

        return formatResult(result);
    }

    private static void removeOutOfWindowElements(Deque<Integer> deque, int i, int k) {
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
            deque.pollFirst();
        }
    }

    private static void removeGreaterElements(Deque<Integer> deque, int[] sequence, int i) {
        while (!deque.isEmpty() && sequence[i] <= sequence[deque.peekLast()]) {
            deque.pollLast();
        }
    }

    private static String formatResult(int[] result) {
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }
}
