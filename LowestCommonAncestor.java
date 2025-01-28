import java.util.*;
import java.io.*;

public class LowestCommonAncestor {
    static int maxDepth = 20;
    static int[] nodeDepth;
    static int[][] ancestor;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int treeSize = Integer.parseInt(reader.readLine());
        nodeDepth = new int[treeSize];
        ancestor = new int[treeSize][maxDepth];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < treeSize; i++) {
            ancestor[i][0] = Integer.parseInt(tokenizer.nextToken());
            nodeDepth[i] = nodeDepth[ancestor[i][0]] + 1;
            for (int j = 1; j < maxDepth; j++) {
                ancestor[i][j] = ancestor[ancestor[i][j - 1]][j - 1];
            }
        }

        int queryCount = Integer.parseInt(reader.readLine());
        StringBuilder resultBuilder = new StringBuilder();
        while (queryCount-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int firstNode = Integer.parseInt(tokenizer.nextToken());
            int secondNode = Integer.parseInt(tokenizer.nextToken());
            resultBuilder.append(findLowestCommonAncestor(firstNode, secondNode)).append('\n');
        }
        System.out.print(resultBuilder);
    }

    static int findLowestCommonAncestor(int firstNode, int secondNode) {
        if (nodeDepth[firstNode] < nodeDepth[secondNode]) {
            int temp = firstNode;
            firstNode = secondNode;
            secondNode = temp;
        }
        for (int i = maxDepth - 1; i >= 0; i--) {
            if (nodeDepth[firstNode] - (1 << i) >= nodeDepth[secondNode]) {
                firstNode = ancestor[firstNode][i];
            }
        }
        if (firstNode == secondNode) {
            return firstNode;
        }
        for (int i = maxDepth - 1; i >= 0; i--) {
            if (ancestor[firstNode][i] != ancestor[secondNode][i]) {
                firstNode = ancestor[firstNode][i];
                secondNode = ancestor[secondNode][i];
            }
        }
        return ancestor[firstNode][0];
    }
}
