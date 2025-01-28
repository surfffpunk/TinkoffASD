import java.util.*;
import java.io.*;

public class MinimumEdgePath {
    static int logDepth = 20;
    static int[][] ancestor;
    static int[][] minWeight;
    static int[] entryTime;
    static int[] exitTime;
    static int currentTime;
    static List<Edge>[] graph;

    static class Edge {
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static void depthFirstSearch(int node, int parent, int cost) {
        entryTime[node] = currentTime++;
        ancestor[0][node] = parent;
        minWeight[0][node] = cost;
        for (int i = 1; i < logDepth; i++) {
            ancestor[i][node] = ancestor[i - 1][ancestor[i - 1][node]];
            minWeight[i][node] = Math.min(minWeight[i - 1][node], minWeight[i - 1][ancestor[i - 1][node]]);
        }
        for (Edge edge : graph[node]) {
            if (edge.vertex != parent)
                depthFirstSearch(edge.vertex, node, edge.weight);
        }
        exitTime[node] = currentTime++;
    }

    static boolean isAncestor(int possibleAncestor, int possibleDescendant) {
        return entryTime[possibleAncestor] <= entryTime[possibleDescendant] && exitTime[possibleDescendant] <= exitTime[possibleAncestor];
    }

    static int findMinimumWeightLCA(int nodeA, int nodeB) {
        int minWeightOnPath = Integer.MAX_VALUE;
        for (int i = logDepth - 1; i >= 0; i--) {
            if (!isAncestor(ancestor[i][nodeA], nodeB)) {
                minWeightOnPath = Math.min(minWeightOnPath, minWeight[i][nodeA]);
                nodeA = ancestor[i][nodeA];
            }
        }

        if (!isAncestor(nodeA, nodeB)) {
            minWeightOnPath = Math.min(minWeightOnPath, minWeight[0][nodeA]);
        }

        for (int i = logDepth - 1; i >= 0; i--) {
            if (!isAncestor(ancestor[i][nodeB], nodeA)) {
                minWeightOnPath = Math.min(minWeightOnPath, minWeight[i][nodeB]);
                nodeB = ancestor[i][nodeB];
            }
        }

        if (!isAncestor(nodeB, nodeA)) {
            minWeightOnPath = Math.min(minWeightOnPath, minWeight[0][nodeB]);
        }

        return minWeightOnPath;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int numOfNodes = Integer.parseInt(reader.readLine());
        logDepth = (int) Math.ceil(Math.log(numOfNodes) / Math.log(2)) + 1;
        ancestor = new int[logDepth][numOfNodes];
        minWeight = new int[logDepth][numOfNodes];
        entryTime = new int[numOfNodes];
        exitTime = new int[numOfNodes];
        graph = new ArrayList[numOfNodes];
        for (int i = 0; i < numOfNodes; i++)
            graph[i] = new ArrayList<>();
        for (int i = 1; i < numOfNodes; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int parent = Integer.parseInt(tokenizer.nextToken());
            int weight = Integer.parseInt(tokenizer.nextToken());
            graph[parent].add(new Edge(i, weight));
            graph[i].add(new Edge(parent, weight));
        }
        depthFirstSearch(0, 0, Integer.MAX_VALUE);
        int numOfQueries = Integer.parseInt(reader.readLine());
        while (numOfQueries-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int nodeA = Integer.parseInt(tokenizer.nextToken());
            int nodeB = Integer.parseInt(tokenizer.nextToken());
            int result = findMinimumWeightLCA(nodeA, nodeB);
            writer.println(result);
        }
        writer.close();
    }
}
