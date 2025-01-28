import java.util.Scanner;

public class FirstElementNotLowerX{
    static void buildSegmentTree(int[] segmentTree, int[] array, int vertex, int left, int right) {
        if (left == right) {
            segmentTree[vertex] = array[left];
        } else {
            int mid = (left + right) / 2;
            buildSegmentTree(segmentTree, array, vertex * 2, left, mid);
            buildSegmentTree(segmentTree, array, vertex * 2 + 1, mid + 1, right);
            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[vertex * 2 + 1]);
        }
    }

    static int getMaxIndex(int[] segmentTree, int vertex, int left, int right, int queryLeft, int queryRight, int threshold) {
        if (queryLeft > queryRight) return Integer.MAX_VALUE;

        if (left == right) return (segmentTree[vertex] >= threshold ? left : Integer.MAX_VALUE);

        int mid = (left + right) / 2;

        if (left < queryLeft) {
            return Math.min(
                    getMaxIndex(segmentTree, vertex * 2, left, mid, queryLeft, Math.min(queryRight, mid), threshold),
                    getMaxIndex(segmentTree, vertex * 2 + 1, mid + 1, right, Math.max(queryLeft, mid + 1), queryRight, threshold)
            );
        }

        if (segmentTree[vertex * 2] >= threshold) {
            return getMaxIndex(segmentTree, vertex * 2, left, mid, queryLeft, Math.min(queryRight, mid), threshold);
        } else {
            return getMaxIndex(segmentTree, vertex * 2 + 1, mid + 1, right, Math.max(queryLeft, mid + 1), queryRight, threshold);
        }
    }

    static void updateSegmentTree(int[] segmentTree, int vertex, int left, int right, int position, int newValue) {
        if (left == right) {
            segmentTree[vertex] = newValue;
        } else {
            int mid = (left + right) / 2;
            if (position <= mid) {
                updateSegmentTree(segmentTree, vertex * 2, left, mid, position, newValue);
            } else {
                updateSegmentTree(segmentTree, vertex * 2 + 1, mid + 1, right, position, newValue);
            }
            segmentTree[vertex] = Math.max(segmentTree[vertex * 2], segmentTree[vertex * 2 + 1]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int numQueries = scanner.nextInt();

        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = scanner.nextInt();
        }

        int[] segmentTree = new int[arraySize * 4];
        buildSegmentTree(segmentTree, array, 1, 0, arraySize - 1);

        for (int query = 0; query < numQueries; query++) {
            char queryType = scanner.next().charAt(0);
            if (queryType == '1') {
                int index = scanner.nextInt();
                int value = scanner.nextInt();
                updateSegmentTree(segmentTree, 1, 0, arraySize - 1, index, value);
            } else {
                int threshold = scanner.nextInt();
                int leftBound = scanner.nextInt();
                int result = getMaxIndex(segmentTree, 1, 0, arraySize - 1, leftBound, arraySize - 1, threshold);
                System.out.println((result == Integer.MAX_VALUE ? -1 : result));
            }
        }
    }
}
