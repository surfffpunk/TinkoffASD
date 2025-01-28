import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Painter {
    static class SegmentTree {
        int count;
        int blackSegments;
        int color;
        int left;
        int right;
        boolean lazy;

        SegmentTree() {
            count = 1;
            blackSegments = 1;
            color = 0;
            left = 0;
            right = 0;
            lazy = false;
        }

        SegmentTree(int c, int seg, int col, int l, int r, boolean lz) {
            count = c;
            blackSegments = seg;
            color = col;
            left = l;
            right = r;
            lazy = lz;
        }
    }

    private SegmentTree[] tree;
    private char[] colors;
    private int[] coordinates;
    private int[] deltas;
    private int n;

    Painter(int n) {
        this.n = n;
        colors = new char[n];
        coordinates = new int[n];
        deltas = new int[n];
    }

    private int getMiddle(int left, int right) {
        return left + (right - left) / 2;
    }

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = new SegmentTree(0, 0, 0, left, right, false);
        } else {
            int middle = getMiddle(left, right);
            build(node * 2, left, middle);
            build(node * 2 + 1, middle + 1, right);
            tree[node] = new SegmentTree(0, 0, 0, left, right, false);
        }
    }

    private void push(int node) {
        if (!tree[node].lazy)
            return;

        tree[node].count = tree[node].color * (tree[node].right - tree[node].left + 1);
        tree[node].blackSegments = 1 * tree[node].color;
        tree[node].lazy = false;

        if (tree[node].left == tree[node].right)
            return;

        tree[node * 2].color = tree[node].color;
        tree[node * 2 + 1].color = tree[node].color;

        tree[node * 2].lazy = true;
        tree[node * 2 + 1].lazy = true;
    }

    private void update(int node, int value, int left, int right) {
        int currentLeft = tree[node].left;
        int currentRight = tree[node].right;

        if (currentRight < left || currentLeft > right)
            return;

        if (currentRight <= right && currentLeft >= left) {
            tree[node].color = value;
            tree[node].lazy = true;
            push(node);
            return;
        }

        push(node);

        int middle = getMiddle(currentLeft, currentRight);
        if (left <= middle) {
            update(node * 2, value, left, right);
        }
        if (right > middle) {
            update(node * 2 + 1, value, left, right);
        }

        boolean leftIsBlack = isRightBlack(node * 2);
        boolean rightIsBlack = isLeftBlack(node * 2 + 1);

        tree[node].count = tree[node * 2].count + tree[node * 2 + 1].count;
        tree[node].blackSegments = tree[node * 2 + 1].blackSegments + tree[node * 2].blackSegments;

        if (leftIsBlack && rightIsBlack) {
            tree[node].blackSegments--;
        }
    }

    private boolean isLeftBlack(int node) {
        push(node);

        while (tree[node].left != tree[node].right) {
            node *= 2;
            push(node);
        }

        return tree[node].count == 1;
    }

    private boolean isRightBlack(int node) {
        push(node);

        while (tree[node].left != tree[node].right) {
            node = node * 2 + 1;
            push(node);
        }

        return tree[node].count == 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        Painter painter = new Painter(n);

        painter.colors = new char[n];
        painter.coordinates = new int[n];
        painter.deltas = new int[n];

        int maxDelta = 0;
        int maxCoord = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            char color = input[0].charAt(0);
            int coord = Integer.parseInt(input[1]);
            int delta = Integer.parseInt(input[2]);

            painter.colors[i] = color;
            painter.coordinates[i] = coord;
            painter.deltas[i] = (delta > 0) ? delta - 1 : delta + 1;

            int del = coord + painter.deltas[i];
            if (del > maxCoord) {
                maxCoord = del;
            }

            if (maxDelta > coord) {
                maxDelta = coord;
            }
        }

        int length;
        if (maxDelta < 0) {
            length = maxCoord - maxDelta + 1;
        } else {
            length = maxCoord + 1;
        }

        painter.tree = new SegmentTree[4 * length];
        painter.build(1, 0, length);
        for (int i = 0; i < n; i++) {
            if (painter.colors[i] == 'W') {
                painter.update(1, 0, painter.coordinates[i] - maxDelta, painter.coordinates[i] + painter.deltas[i] - maxDelta);
                System.out.println(painter.tree[1].blackSegments + " " + painter.tree[1].count);
            }

            if (painter.colors[i] == 'B') {
                painter.update(1, 1, painter.coordinates[i] - maxDelta, painter.coordinates[i] + painter.deltas[i] - maxDelta);
                System.out.println(painter.tree[1].blackSegments + " " + painter.tree[1].count);
            }
        }
    }
}
