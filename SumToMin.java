import java.util.*;
import java.io.*;

public class SumToMin {
    static class SegmentTree {
        long[] tree;
        long[] lazyUpdate;
        int size;

        SegmentTree(int size) {
            this.size = size;
            tree = new long[size * 4];
            lazyUpdate = new long[size * 4];
        }

        void updateSegment(int node, int start, int end, int left, int right, long value) {
            if (lazyUpdate[node] != 0) {
                tree[node] += lazyUpdate[node];
                if (start != end) {
                    lazyUpdate[node * 2] += lazyUpdate[node];
                    lazyUpdate[node * 2 + 1] += lazyUpdate[node];
                }
                lazyUpdate[node] = 0;
            }
            if (start > end || start > right || end < left)
                return;
            if (start >= left && end <= right) {
                tree[node] += value;
                if (start != end) {
                    lazyUpdate[node * 2] += value;
                    lazyUpdate[node * 2 + 1] += value;
                }
                return;
            }
            updateSegment(node * 2, start, (start + end) / 2, left, right, value);
            updateSegment(node * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }

        long querySegment(int node, int start, int end, int left, int right) {
            if (start > end || start > right || end < left)
                return Long.MAX_VALUE;
            if (lazyUpdate[node] != 0) {
                tree[node] += lazyUpdate[node];
                if (start != end) {
                    lazyUpdate[node * 2] += lazyUpdate[node];
                    lazyUpdate[node * 2 + 1] += lazyUpdate[node];
                }
                lazyUpdate[node] = 0;
            }
            if (start >= left && end <= right)
                return tree[node];
            long minLeft = querySegment(node * 2, start, (start + end) / 2, left, right);
            long minRight = querySegment(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return Math.min(minLeft, minRight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int arraySize = Integer.parseInt(tokenizer.nextToken());
        int numOperations = Integer.parseInt(tokenizer.nextToken());
        SegmentTree segmentTree = new SegmentTree(arraySize);
        while (numOperations-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int operationType = Integer.parseInt(tokenizer.nextToken());
            if (operationType == 1) {
                int left = Integer.parseInt(tokenizer.nextToken());
                int right = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                segmentTree.updateSegment(1, 0, arraySize - 1, left, right - 1, value);
            } else {
                int left = Integer.parseInt(tokenizer.nextToken());
                int right = Integer.parseInt(tokenizer.nextToken());
                writer.println(segmentTree.querySegment(1, 0, arraySize - 1, left, right - 1));
            }
        }
        writer.close();
    }
}
