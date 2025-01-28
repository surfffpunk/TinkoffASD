import java.util.*;

public class WeaknessOfArmy {
    static final int MAX_SIZE = 1000007;
    static int[] numbers = new int[MAX_SIZE];

    static class SegmentTree {
        ArrayList<Integer> values = new ArrayList<>();
        SegmentTree left, right;

        int sum(int a, int b) {
            return a + b;
        }

        SegmentTree build(int start, int end) {
            if (start == end)
                values.add(numbers[start]);
            else {
                left = new SegmentTree().build(start, (start + end) / 2);
                right = new SegmentTree().build((start + end) / 2 + 1, end);
                values = new ArrayList<>();
                values.addAll(left.values);
                values.addAll(right.values);
                Collections.sort(values);
            }
            return this;
        }

        int lessThanQuery(int start, int end, int a, int b, int x) {
            if (start > b || end < a)
                return 0;
            else if (start >= a && end <= b)
                return lowerBound(values, x);
            else
                return sum(left.lessThanQuery(start, (start + end) / 2, a, b, x), right.lessThanQuery((start + end) / 2 + 1, end, a, b, x));
        }

        int greaterThanQuery(int start, int end, int a, int b, int x) {
            if (start > b || end < a)
                return 0;
            else if (start >= a && end <= b)
                return values.size() - upperBound(values, x);
            else
                return sum(left.greaterThanQuery(start, (start + end) / 2, a, b, x), right.greaterThanQuery((start + end) / 2 + 1, end, a, b, x));
        }

        int lowerBound(ArrayList<Integer> arr, int target) {
            int low = 0, high = arr.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr.get(mid) < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        int upperBound(ArrayList<Integer> arr, int target) {
            int low = 0, high = arr.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr.get(mid) <= target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        for (int i = 1; i <= size; i++)
            numbers[i] = scanner.nextInt();

        SegmentTree root = new SegmentTree().build(1, size);
        long result = 0;

        for (int i = 2; i <= size - 1; i++) {
            int greaterCount = root.greaterThanQuery(1, size, 1, i - 1, numbers[i]);
            int lessCount = root.lessThanQuery(1, size, i + 1, size, numbers[i]);
            result += (long) greaterCount * lessCount;
        }

        System.out.println(result);
    }
}
