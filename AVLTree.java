import java.util.*;

public class AVLTree {
    static TreeNode[] tree;
    static final int INT_MIN = Integer.MIN_VALUE;
    static final int INT_MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int root = scanner.nextInt();

        tree = new TreeNode[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new TreeNode();
            tree[i].left = scanner.nextInt();
            tree[i].right = scanner.nextInt();
        }

        if (isAVL(root)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static int getHeight(int root) {
        if (root == -1)
            return 0;

        return 1 + Math.max(getHeight(tree[root].left), getHeight(tree[root].right));
    }

    static boolean isBST(int root, int minValue, int maxValue) {
        if (root == -1)
            return true;

        if (root < minValue || root > maxValue)
            return false;
        return isBST(tree[root].left, minValue, root - 1) && isBST(tree[root].right, root + 1, maxValue);
    }

    static boolean isAVL(int root) {
        if (root == -1)
            return true;

        int leftHeight = getHeight(tree[root].left);
        int rightHeight = getHeight(tree[root].right);

        if (Math.abs(leftHeight - rightHeight) > 1)
            return false;

        if (!isBST(tree[root].left, INT_MIN, root - 1) || !isBST(tree[root].right, root + 1, INT_MAX))
            return false;
        return isAVL(tree[root].left) && isAVL(tree[root].right);
    }
}

class TreeNode {
    int left;
    int right;
}
