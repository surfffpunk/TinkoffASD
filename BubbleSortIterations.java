import java.util.ArrayList;
import java.util.Scanner;

public class BubbleSortIterations {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            ArrayList<Integer> positions = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                positions.add(0);
            }

            int counter = 0;
            int indexLeft = n - 1;
            ArrayList<Integer> answer = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int k = scanner.nextInt();
                positions.set(k - 1, 1);
                counter++;

                while (positions.get(indexLeft) != 0 && indexLeft > 0) {
                    indexLeft--;
                    counter--;
                }

                answer.add(counter + 1);
            }

            System.out.print("1 ");
            for (int i = 0; i < answer.size() - 1; i++) {
                System.out.print(answer.get(i) + " ");
            }
            System.out.println("1");
        }
    }
}
