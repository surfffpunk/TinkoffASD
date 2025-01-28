import java.util.*;

public class CycleDetection {
    static List<Integer>[] graph;
    static int[] state;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();

        graph = new ArrayList[vertices+1];
        for (int i = 1; i <= vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            int startVertex = scanner.nextInt();
            int endVertex = scanner.nextInt();
            graph[startVertex].add(endVertex);
        }

        state = new int[vertices+1];
        for (int i = 1; i <= vertices; i++) {
            if (state[i] == 0) {
                if (hasCycle(i)) {
                    System.out.println(1);
                    return;
                }
            }
        }
        System.out.println(0);
    }

    static boolean hasCycle(int vertex) {
        state[vertex] = 1;
        for (int neighbour : graph[vertex]) {
            if (state[neighbour] == 1) {
                return true;
            } else if (state[neighbour] == 0) {
                if (hasCycle(neighbour)) {
                    return true;
                }
            }
        }
        state[vertex] = 2;
        return false;
    }
}
