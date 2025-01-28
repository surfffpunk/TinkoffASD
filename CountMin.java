import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CountMin {
    static class SegmentTreeNode {
        int minValue;
        int countOfMinValue;

        public SegmentTreeNode(int minValue, int countOfMinValue) {
            this.minValue = minValue;
            this.countOfMinValue = countOfMinValue;
        }
    }

    static SegmentTreeNode[] segmentTree;

    static SegmentTreeNode mergeNodes(SegmentTreeNode node1, SegmentTreeNode node2) {
        if (node1.minValue < node2.minValue)
            return node1;
        if (node1.minValue > node2.minValue)
            return node2;
        return new SegmentTreeNode(node1.minValue, node1.countOfMinValue + node2.countOfMinValue);
    }

    static void buildSegmentTree(int[] array, int vertex, int left, int right) {
        if (left == right) {
            segmentTree[vertex] = new SegmentTreeNode(array[left], 1);
        } else {
            int mid = (left + right) / 2;
            buildSegmentTree(array, vertex * 2, left, mid);
            buildSegmentTree(array, vertex * 2 + 1, mid + 1, right);
            segmentTree[vertex] = mergeNodes(segmentTree[vertex * 2], segmentTree[vertex * 2 + 1]);
        }
    }

    static SegmentTreeNode query(int vertex, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > queryRight)
            return new SegmentTreeNode(Integer.MAX_VALUE, 0);
        if (queryLeft == left && queryRight == right)
            return segmentTree[vertex];
        int mid = (left + right) / 2;
        return mergeNodes(
                query(vertex * 2, left, mid, queryLeft, Math.min(queryRight, mid)),
                query(vertex * 2 + 1, mid + 1, right, Math.max(queryLeft, mid + 1), queryRight)
        );
    }

    static void update(int vertex, int left, int right, int index, int newValue) {
        if (left == right) {
            segmentTree[vertex] = new SegmentTreeNode(newValue, 1);
        } else {
            int mid = (left + right) / 2;
            if (index <= mid)
                update(vertex * 2, left, mid, index, newValue);
            else
                update(vertex * 2 + 1, mid + 1, right, index, newValue);
            segmentTree[vertex] = mergeNodes(segmentTree[vertex * 2], segmentTree[vertex * 2 + 1]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int arraySize = Integer.parseInt(tokenizer.nextToken());
        int operationsCount = Integer.parseInt(tokenizer.nextToken());
        int[] array = new int[arraySize];
        segmentTree = new SegmentTreeNode[4 * arraySize];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < arraySize; i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
        buildSegmentTree(array, 1, 0, arraySize - 1);
        while (operationsCount-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            int operationType = Integer.parseInt(tokenizer.nextToken());
            if (operationType == 1) {
                int index = Integer.parseInt(tokenizer.nextToken());
                int value = Integer.parseInt(tokenizer.nextToken());
                update(1, 0, arraySize - 1, index, value);
            } else {
                int left = Integer.parseInt(tokenizer.nextToken());
                int right = Integer.parseInt(tokenizer.nextToken()) - 1;
                SegmentTreeNode result = query(1, 0, arraySize - 1, left, right);
                writer.println(result.minValue + " " + result.countOfMinValue);
            }
        }
        writer.close();
    }
}
