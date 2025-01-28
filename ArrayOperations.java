import java.util.*;
import java.io.*;

public class ArrayOperations {
    static class LazySegmentTree {
        long[] tree;
        long[] lazy;
        int size;

        LazySegmentTree(int n) {
            size = n;
            tree = new long[4 * n];
            lazy = new long[4 * n];
        }

        void push(int node, int start, int end) {
            if (lazy[node] != 0) {
                tree[node] = lazy[node] * (end - start + 1);
                if (start != end) {
                    lazy[node * 2] = lazy[node];
                    lazy[node * 2 + 1] = lazy[node];
                }
                lazy[node] = 0;
            }
        }

        void assignOrAddValue(int node, int start, int end, int left, int right, long value, boolean isAssignment) {
            push(node, start, end);
            if (start > end || start > right || end < left)
                return;
            if (start >= left && end <= right) {
                if (isAssignment) {
                    tree[node] = value * (end - start + 1);
                    if (start != end) {
                        lazy[node * 2] = value;
                        lazy[node * 2 + 1] = value;
                    }
                } else {
                    tree[node] += value * (end - start + 1);
                    if (start != end) {
                        lazy[node * 2] += value;
                        lazy[node * 2 + 1] += value;
                    }
                }
                return;
            }
            int mid = (start + end) / 2;
            assignOrAddValue(node * 2, start, mid, left, right, value, isAssignment);
            assignOrAddValue(node * 2 + 1, mid + 1, end, left, right, value, isAssignment);
            push(node * 2, start, mid);
            push(node * 2 + 1, mid + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        long calculateSum(int node, int start, int end, int left, int right) {
            push(node, start, end);
            if (start > end || start > right || end < left)
                return 0;
            if (start >= left && end <= right)
                return tree[node];
            int mid = (start + end) / 2;
            long sumLeft = calculateSum(node * 2, start, mid, left, right);
            long sumRight = calculateSum(node * 2 + 1, mid + 1, end, left, right);
            return (sumLeft + sumRight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arraySizeAndOperationsCount = reader.readLine().split(" ");
        int arraySize = Integer.parseInt(arraySizeAndOperationsCount[0]);
        int operationsCount = Integer.parseInt(arraySizeAndOperationsCount[1]);
        LazySegmentTree segmentTree = new LazySegmentTree(arraySize);
        for (int i = 0; i < operationsCount; i++) {
            String[] operation = reader.readLine().split(" ");
            int operationType = Integer.parseInt(operation[0]);
            int left = Integer.parseInt(operation[1]);
            int right = Integer.parseInt(operation[2]);
            if (operationType == 1) {
                long value = Long.parseLong(operation[3]);
                segmentTree.assignOrAddValue(1, 0, arraySize - 1, left, right - 1, value, true);
            } else if (operationType == 2) {
                long value = Long.parseLong(operation[3]);
                segmentTree.assignOrAddValue(1, 0, arraySize - 1, left, right - 1, value, false);
            } else if (operationType == 3) {
                System.out.println(segmentTree.calculateSum(1, 0, arraySize - 1, left, right - 1));
            }
        }
    }
}
