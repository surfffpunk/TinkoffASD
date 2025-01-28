import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DisjointSetSystem {

    static int[] parent, depth, minElement, maxElement, count;

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (depth[x] < depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        if (depth[x] == depth[y]) depth[x]++;
        count[x] += count[y];
        minElement[x] = Math.min(minElement[x], minElement[y]);
        maxElement[x] = Math.max(maxElement[x], maxElement[y]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        parent = new int[n + 1];
        depth = new int[n + 1];
        minElement = new int[n + 1];
        maxElement = new int[n + 1];
        count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            depth[i] = 0;
            minElement[i] = i;
            maxElement[i] = i;
            count[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String query = tokenizer.nextToken();
            if (query.equals("union")) {
                int u = Integer.parseInt(tokenizer.nextToken());
                int v = Integer.parseInt(tokenizer.nextToken());
                union(u, v);
            } else {
                int u = Integer.parseInt(tokenizer.nextToken());
                int u1 = find(u);
                System.out.println(minElement[u1] + " " + maxElement[u1] + " " + count[u1]);
            }
        }

        reader.close();
    }
}
