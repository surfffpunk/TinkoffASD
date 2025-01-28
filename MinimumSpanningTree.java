import java.util.*;

public class MinimumSpanningTree {
    static class Edge implements Comparable<Edge> {
        int startVertex, endVertex;
        long weight;

        Edge(int startVertex, int endVertex, long weight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Long.compare(this.weight, other.weight);
        }
    }

    static class DisjointSetUnion {
        private final int[] parent;
        private final int[] size;

        DisjointSetUnion(int size) {
            parent = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                this.size[i] = 1;
            }
        }

        int find(int vertex) {
            if (vertex != parent[vertex])
                parent[vertex] = find(parent[vertex]);
            return parent[vertex];
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numVertices = scanner.nextInt();
        int numEdges = scanner.nextInt();
        List<Edge> edges = new ArrayList<>(numEdges);

        for (int i = 0; i < numEdges; ++i) {
            int startVertex = scanner.nextInt() - 1;
            int endVertex = scanner.nextInt() - 1;
            long weight = scanner.nextLong();
            edges.add(new Edge(startVertex, endVertex, weight));
        }

        Collections.sort(edges);

        DisjointSetUnion dsu = new DisjointSetUnion(numVertices);
        long result = 0;

        for (Edge edge : edges) {
            int endVertex = edge.endVertex;
            int startVertex = edge.startVertex;
            long weight = edge.weight;
            if (dsu.find(endVertex) != dsu.find(startVertex)) {
                result += weight;
                dsu.union(endVertex, startVertex);
            }
        }

        System.out.println(result);
    }
}
