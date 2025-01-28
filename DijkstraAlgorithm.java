import java.util.*;

public class DijkstraAlgorithm {

    static long INFINITY = 4000000001L;

    static long[] findShortestPaths(List<List<Edge>> graph, int numberOfNodes) {
        long[] distances = new long[numberOfNodes];
        Arrays.fill(distances, INFINITY);
        int startNode = 0;
        distances[startNode] = 0;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
        priorityQueue.offer(new Edge(0, startNode));

        while (!priorityQueue.isEmpty()) {
            Edge top = priorityQueue.poll();
            int currentNode = top.node;

            for (Edge nextEdge : graph.get(currentNode)) {
                int adjacentNode = nextEdge.node;
                int edgeWeight = (int) nextEdge.weight;
                if (distances[adjacentNode] > distances[currentNode] + edgeWeight) {
                    distances[adjacentNode] = distances[currentNode] + edgeWeight;
                    priorityQueue.offer(new Edge(distances[adjacentNode], adjacentNode));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = scanner.nextInt();
        int numberOfEdges = scanner.nextInt();

        List<List<Edge>> graph = new ArrayList<>(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            int sourceNode = scanner.nextInt() - 1;
            int destinationNode = scanner.nextInt() - 1;
            int edgeWeight = scanner.nextInt();
            graph.get(sourceNode).add(new Edge(destinationNode, edgeWeight));
            graph.get(destinationNode).add(new Edge(sourceNode, edgeWeight));
        }

        long[] shortestDistances = findShortestPaths(graph, numberOfNodes);
        for (long distance : shortestDistances) {
            System.out.print(distance + " ");
        }
    }

    static class Edge {
        int node;
        long weight;

        Edge(long weight, int node) {
            this.weight = weight;
            this.node = node;
        }
    }
}
