import java.util.*;

class GraphEdge implements Comparable<GraphEdge> {
    int source, destination, weight;
    GraphEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public int compareTo(GraphEdge other) {
        return this.weight - other.weight;
    }
}

class DisjointSetUnion {
    int rows, columns, totalSize;
    int[] parent;
    DisjointSetUnion(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.totalSize = rows * columns;
        this.parent = new int[totalSize];
        for(int i = 0; i < totalSize; i++)
            parent[i] = i;
    }
    int encode(int row, int column) {
        return row * columns + column;
    }
    int find(int i) {
        if(i == parent[i])
            return i;
        return parent[i] = find(parent[i]);
    }
    boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y)
            return false;
        parent[x] = y;
        return true;
    }
}

public class DisjointSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        DisjointSetUnion dsu = new DisjointSetUnion(rows, columns);
        ArrayList<GraphEdge> edges = new ArrayList<>();
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++){
                int value = scanner.nextInt();
                if((value & 1) != 0)
                    edges.add(new GraphEdge(dsu.encode(i, j), dsu.encode(i + 1, j), 0));
                if((value >> 1) != 0)
                    edges.add(new GraphEdge(dsu.encode(i, j), dsu.encode(i, j + 1), 0));
            }
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++){
                if(i + 1 != rows)
                    edges.add(new GraphEdge(dsu.encode(i, j), dsu.encode(i + 1, j), 1));
                if(j + 1 != columns)
                    edges.add(new GraphEdge(dsu.encode(i, j), dsu.encode(i, j + 1), 2));
            }

        ArrayList<GraphEdge> result = new ArrayList<>();
        Collections.sort(edges);
        for(GraphEdge edge : edges){
            if(dsu.union(edge.source, edge.destination) && edge.weight != 0)
                result.add(edge);
        }

        System.out.println(result.size() + " " + result.stream().mapToInt(edge -> edge.weight).sum());
        for(GraphEdge edge : result){
            int row = edge.source / columns + 1;
            int column = edge.source % columns + 1;
            int direction = 1 + ((edge.destination / columns) == (edge.source / columns) ? 1 : 0);
            System.out.println(row + " " + column + " " + direction);
        }
    }
}
