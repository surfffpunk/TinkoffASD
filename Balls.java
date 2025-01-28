import java.util.Scanner;
import java.util.ArrayList;

public class Balls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> balls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            balls.add(scanner.nextInt());
        }
        System.out.println(countDestroyedBalls(balls));
    }

    public static int countDestroyedBalls(ArrayList<Integer> balls) {
        int count = 0;
        boolean found;
        do {
            found = false;
            for (int i = 0; i < balls.size() - 2; i++) {
                if (balls.get(i).equals(balls.get(i+1)) && balls.get(i).equals(balls.get(i+2))) {
                    int j = i + 3;
                    while (j < balls.size() && balls.get(i).equals(balls.get(j))) {
                        j++;
                    }
                    int destroyed = j - i;
                    count += destroyed;
                    balls.subList(i, j).clear();
                    found = true;
                    break;
                }
            }
        } while (found);
        return count;
    }
}
