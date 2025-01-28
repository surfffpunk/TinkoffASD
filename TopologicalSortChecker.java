import java.util.*;

public class TopologicalSortChecker {
    private static int[] nodePositions;
    private static List<Integer>[] graph;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        initializeGraph(nodeCount);
        readEdges(scanner, edgeCount);
        int[] permutation = readPermutation(scanner, nodeCount);

        if (isTopologicalSort(permutation)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void initializeGraph(int nodeCount) {
        nodePositions = new int[nodeCount + 1];
        graph = new ArrayList[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    private static void readEdges(Scanner scanner, int edgeCount) {
        for (int i = 0; i < edgeCount; i++) {
            int fromNode = scanner.nextInt();
            int toNode = scanner.nextInt();
            graph[fromNode].add(toNode);
        }
    }

    private static int[] readPermutation(Scanner scanner, int nodeCount) {
        int[] permutation = new int[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            permutation[i] = scanner.nextInt();
            nodePositions[permutation[i]] = i;
        }
        return permutation;
    }

    private static boolean isTopologicalSort(int[] permutation) {
        for (int i = 1; i < permutation.length; i++) {
            for (int adjacentNode : graph[permutation[i]]) {
                if (nodePositions[permutation[i]] > nodePositions[adjacentNode]) {
                    return false;
                }
            }
        }
        return true;
    }
}
