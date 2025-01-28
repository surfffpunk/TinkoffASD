import java.util.*;
import java.io.*;

public class RangeOperations {
    static class SegmentTree {
        long[] t;
        long[] add;
        long[] set;
        int n;

        SegmentTree(int n) {
            this.n = n;
            t = new long[4 * n];
            add = new long[4 * n];
            set = new long[4 * n];
            Arrays.fill(set, -1);
        }

        void push(int v, int tl, int tr) {
            if (set[v] != -1) {
                t[v] = set[v] * (tr - tl);
                if (tl != tr - 1) {
                    set[2 * v + 1] = set[v];
                    set[2 * v + 2] = set[v];
                    add[2 * v + 1] = 0;
                    add[2 * v + 2] = 0;
                }
                set[v] = -1;
            }
            t[v] += add[v] * (tr - tl);
            if (tl != tr - 1) {
                add[2 * v + 1] += add[v];
                add[2 * v + 2] += add[v];
            }
            add[v] = 0;
        }

        void assign(int v, int tl, int tr, int l, int r, long val) {
            push(v, tl, tr);
            if (l >= r) return;
            if (tl == l && tr == r) {
                set[v] = val;
                add[v] = 0;
                push(v, tl, tr);
                return;
            }
            int tm = (tl + tr) / 2;
            assign(2 * v + 1, tl, tm, l, Math.min(r, tm), val);
            assign(2 * v + 2, tm, tr, Math.max(l, tm), r, val);
            t[v] = t[2 * v + 1] + t[2 * v + 2];
        }

        void add(int v, int tl, int tr, int l, int r, long val) {
            push(v, tl, tr);
            if (l >= r) return;
            if (tl == l && tr == r) {
                add[v] += val;
                push(v, tl, tr);
                return;
            }
            int tm = (tl + tr) / 2;
            add(2 * v + 1, tl, tm, l, Math.min(r, tm), val);
            add(2 * v + 2, tm, tr, Math.max(l, tm), r, val);
            t[v] = t[2 * v + 1] + t[2 * v + 2];
        }

        long sum(int v, int tl, int tr, int l, int r) {
            push(v, tl, tr);
            if (l >= r) return 0;
            if (tl == l && tr == r) return t[v];
            int tm = (tl + tr) / 2;
            return sum(2 * v + 1, tl, tm, l, Math.min(r, tm)) +
                    sum(2 * v + 2, tm, tr, Math.max(l, tm), r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        SegmentTree tree = new SegmentTree(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (type == 1) {
                long v = Long.parseLong(st.nextToken());
                tree.assign(0, 0, n, l, r, v);
            } else if (type == 2) {
                long v = Long.parseLong(st.nextToken());
                tree.add(0, 0, n, l, r, v);
            } else {
                out.println(tree.sum(0, 0, n, l, r));
            }
        }
        out.close();
    }
}
