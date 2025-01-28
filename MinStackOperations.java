import java.util.*;
import java.io.*;

public class MinStackOperations {
    public static void main(String[] args) throws IOException {
        performOperations();
    }

    private static void performOperations() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfOperations = Integer.parseInt(reader.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> minStack = new ArrayDeque<>();
        List<Integer> minValues = new ArrayList<>();

        for (int i = 0; i < numberOfOperations; i++) {
            String[] operation = reader.readLine().split(" ");
            switch (operation[0]) {
                case "1":
                    handlePushOperation(stack, minStack, Integer.parseInt(operation[1]));
                    break;
                case "2":
                    handlePopOperation(stack, minStack);
                    break;
                case "3":
                    minValues.add(minStack.peek());
                    break;
            }
        }

        printMinValues(minValues);
    }

    private static void handlePushOperation(Deque<Integer> stack, Deque<Integer> minStack, int value) {
        stack.push(value);
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
    }

    private static void handlePopOperation(Deque<Integer> stack, Deque<Integer> minStack) {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    private static void printMinValues(List<Integer> minValues) {
        for (Integer minValue : minValues) {
            System.out.println(minValue);
        }
    }
}
