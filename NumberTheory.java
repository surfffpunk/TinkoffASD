import java.util.*;

class NumberTheory {
    static final int MAX = 100005;
    static int num;
    static ArrayList<Pair> graph[] = new ArrayList[MAX];

    static class Pair implements Comparable<Pair> {
        int node, weight;

        Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public int compareTo(Pair pair) {
            return this.weight - pair.weight;
        }
    }

    static void dijkstra(int source) {
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        int[] distance = new int[num + 2];
        Arrays.fill(distance, Integer.MAX_VALUE);
        queue.add(new Pair(source, 0));
        distance[source] = 0;
        while (!queue.isEmpty()) {
            int current = queue.peek().node;
            queue.poll();
            for (Pair neighbour : graph[current]) {
                int nextNode = neighbour.node;
                int edgeWeight = neighbour.weight;
                if (distance[nextNode] > distance[current] + edgeWeight) {
                    distance[nextNode] = distance[current] + edgeWeight;
                    queue.add(new Pair(nextNode, distance[nextNode]));
                }
            }
        }
        System.out.println(1 + distance[0]);
    }

    static void initializeGraph(int num) {
        for (int i = 1; i <= num; ++i) {
            int from = i % num;
            int to = (i + 1) % num;
            int weight = 1;
            graph[from].add(new Pair(to, weight));
        }
        for (int i = 1; i <= num; ++i) {
            int from = i % num;
            int to = (10 * i) % num;
            int weight = 0;
            graph[from].add(new Pair(to, weight));
        }
        dijkstra(1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        for (int i = 0; i < MAX; i++) {
            graph[i] = new ArrayList<>();
        }
        initializeGraph(num);
    }
}
