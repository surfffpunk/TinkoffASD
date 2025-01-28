import java.util.*;

public class Astrogard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int cnt = 0;

        Deque<Integer> deq = new ArrayDeque<>();
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            switch (x) {
                case 1:
                    int id = scanner.nextInt();
                    mp.put(id, cnt + deq.size());
                    deq.addLast(id);
                    break;
                case 2:
                    mp.remove(deq.getFirst());
                    deq.removeFirst();
                    ++cnt;
                    break;
                case 3:
                    mp.remove(deq.getLast());
                    deq.removeLast();
                    break;
                case 4:
                    id = scanner.nextInt();
                    System.out.println(mp.get(id) - cnt);
                    break;
                case 5:
                    System.out.println(deq.getFirst());
                    break;
            }
        }
    }
}
