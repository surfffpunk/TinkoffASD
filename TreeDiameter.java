import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeDiameter {

    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int maxDepth = 0;
    static int diameter = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            tree[parent].add(i);
        }

        depth = new int[n];
        dfs(0, -1);

        System.out.println(maxDepth + " " + diameter);

        for (int i = 0; i < n; i++) {
            System.out.print(depth[i] + " ");
        }
    }

    static int dfs(int node, int parent) {
        depth[node] = (parent == -1) ? 0 : depth[parent] + 1;
        maxDepth = Math.max(maxDepth, depth[node]);

        int max1 = -1, max2 = -1;
        for (int child : tree[node]) {
            if (child != parent) {
                int childDepth = dfs(child, node);

                if (childDepth > max1) {
                    max2 = max1;
                    max1 = childDepth;
                } else if (childDepth > max2) {
                    max2 = childDepth;
                }
            }
        }

        diameter = Math.max(diameter, max1 + max2 + 2);
        return max1 + 1;
    }
}
