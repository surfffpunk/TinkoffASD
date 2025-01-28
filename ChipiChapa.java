import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChipiChapa {
    static long[] segmentTree;
    static long[] videoFragments;

    static void buildTree(int node, int start, int end) {
        if (start == end) {
            segmentTree[node] = videoFragments[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(2*node, start, mid);
            buildTree(2*node+1, mid+1, end);
            segmentTree[node] = segmentTree[2*node] + segmentTree[2*node+1];
        }
    }

    static void updateTree(int node, int start, int end, int idx, long val) {
        if (start == end) {
            segmentTree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid)
                updateTree(2*node, start, mid, idx, val);
            else
                updateTree(2*node+1, mid+1, end, idx, val);
            segmentTree[node] = segmentTree[2*node] + segmentTree[2*node+1];
        }
    }

    static long queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        long p1 = queryTree(2*node, start, mid, l, r);
        long p2 = queryTree(2*node+1, mid+1, end, l, r);
        return p1 + p2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        videoFragments = new long[n];
        segmentTree = new long[4*n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            videoFragments[i] = Long.parseLong(st.nextToken());
        }

        buildTree(1, 0, n-1);

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int idx = Integer.parseInt(st.nextToken());
                long val = Long.parseLong(st.nextToken());
                updateTree(1, 0, n-1, idx, val);
            } else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken()) - 1;
                System.out.println(queryTree(1, 0, n-1, l, r));
            }
        }
    }
}
