import java.util.*;

public class ConnectedComponents {
    static int nodeCount;
    static int edgeCount;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static ArrayList<Integer> component;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        nodeCount = scanner.nextInt();
        edgeCount = scanner.nextInt();
        graph = new ArrayList[nodeCount+1];
        visited = new boolean[nodeCount+1];
        for (int i = 1; i <= nodeCount; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edgeCount; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        for (int i = 1; i <= nodeCount; i++) {
            if (!visited[i]) {
                component = new ArrayList<>();
                depthFirstSearch(i);
                Collections.sort(component);
                components.add(component);
            }
        }
        System.out.println(components.size());
        for (ArrayList<Integer> comp : components) {
            System.out.println(comp.size());
            for (int i : comp) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    static void depthFirstSearch(int node) {
        visited[node] = true;
        component.add(node);
        for (int connectedNode : graph[node]) {
            if (!visited[connectedNode]) {
                depthFirstSearch(connectedNode);
            }
        }
    }
}
