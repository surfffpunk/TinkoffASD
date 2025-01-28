import java.util.*;
import java.io.*;

public class Goblins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> middle = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String command = reader.readLine();
            if (command.charAt(0) == '+') {
                queue.add(Integer.parseInt(command.substring(2)));
                balanceQueues(queue, middle);
            } else if (command.charAt(0) == '*') {
                queue.addFirst(Integer.parseInt(command.substring(2)));
                balanceQueues(queue, middle);
            } else {
                output.add(middle.get(0));
                middle.remove(0);
                balanceQueues(queue, middle);
            }
        }

        for (Integer goblin : output) {
            System.out.println(goblin);
        }
    }

    private static void balanceQueues(Deque<Integer> queue, List<Integer> middle) {
        if (queue.size() > middle.size()) {
            middle.add(queue.poll());
        }
    }
}
