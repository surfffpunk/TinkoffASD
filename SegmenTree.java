import java.util.*;
import java.io.*;

public class SegmenTree {
    private static int[] tree;
    private static int[] array;
    private static int size;

    private static void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(node * 2, start, mid);
            buildTree(node * 2 + 1, mid + 1, end);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private static void updateTree(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid)
                updateTree(node * 2, start, mid, index, value);
            else
                updateTree(node * 2 + 1, mid + 1, end, index, value);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private static int queryTree(int node, int start, int end, int left, int x) {
        if (tree[node] <= x || end < left)
            return -1;
        if (start == end)
            return start;
        int mid = (start + end) / 2;
        int result = queryTree(node * 2, start, mid, left + 1, x);
        if (result == -1)
            result = queryTree(node * 2 + 1, mid + 1, end, left + 1, x);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        int operations = Integer.parseInt(tokenizer.nextToken());
        array = new int[size];
        tree = new int[4 * size];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
        buildTree(1, 0, size - 1);
        for (int i = 0; i < operations; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            if (type == 1) {
                int index = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                updateTree(1, 0, size - 1, index, value);
            } else {
                int x = Integer.parseInt(tokenizer.nextToken());
                int left = Integer.parseInt(tokenizer.nextToken());
                System.out.println(queryTree(1, 0, size - 1, left + 1, x));
            }
        }
    }
}
