import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IncreasingSubsequencesWithSegmentTree {
    private static final int MOD = (int) 1e9 + 7;
    private static int sequenceLength;
    private static int[] sequence, longestIncreasingSubsequence, countOfSubsequences, segmentTree;

    public static void main(String[] args) throws Exception {
        readInput();
        calculateLongestIncreasingSubsequences();
        printCountOfLongestIncreasingSubsequences();
    }

    private static void readInput() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        sequenceLength = Integer.parseInt(reader.readLine());
        sequence = new int[sequenceLength];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < sequenceLength; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
        longestIncreasingSubsequence = new int[sequenceLength];
        countOfSubsequences = new int[sequenceLength];
        // Size of segment tree will be 4 times the length of the input sequence
        segmentTree = new int[4 * sequenceLength];
    }

    private static void calculateLongestIncreasingSubsequences() {
        for (int i = 0; i < sequenceLength; i++) {
            update(0, sequence[i], 1, 0, sequenceLength - 1);
            longestIncreasingSubsequence[i] = query(0, sequence[i] - 1, 0, sequenceLength - 1);
            countOfSubsequences[i] = longestIncreasingSubsequence[i] == 0 ? 1 : query(longestIncreasingSubsequence[i], sequence[i] - 1, 0, sequenceLength - 1);
            update(0, sequence[i], countOfSubsequences[i], 0, sequenceLength - 1);
        }
    }

    private static void update(int node, int value, int count, int start, int end) {
        if (start > value || end < value)
            return;
        if (start == end) {
            segmentTree[node] = count;
            return;
        }
        int mid = (start + end) / 2;
        update(2 * node + 1, value, count, start, mid);
        update(2 * node + 2, value, count, mid + 1, end);
        segmentTree[node] = (segmentTree[2 * node + 1] + segmentTree[2 * node + 2]) % MOD;
    }

    private static int query(int qlow, int qhigh, int low, int high) {
        if (qlow > high || qhigh < low)
            return 0;
        if (qlow <= low && qhigh >= high)
            return segmentTree[0];
        int mid = (low + high) / 2;
        return (query(qlow, qhigh, low, mid) + query(qlow, qhigh, mid + 1, high)) % MOD;
    }

    private static void printCountOfLongestIncreasingSubsequences() {
        System.out.println(countOfSubsequences[sequenceLength - 1]);
    }
}
