import java.util.Scanner;

public class TableCut {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long t = scanner.nextLong();

        long[] nValues = new long[(int) t];
        long[] mValues = new long[(int) t];

        for (int k = 0; k < t; k++) {
            nValues[k] = scanner.nextLong();
            mValues[k] = scanner.nextLong();
        }

        for (int k = 0; k < t; k++) {
            long n = nValues[k];
            long m = mValues[k];
            long best = (long) 1e18;
            char x = 'V';
            int y = 0;
            long sumAll = n * m * (n * m + 1) / 2;
            long l = 0;
            long r = m;

            while (l < r - 1) {
                long mid = (l + r) / 2;
                long nowSum = mid * n * (mid + 1 + m * n - m) / 2;

                if (nowSum <= sumAll / 2) {
                    l = mid;
                } else {
                    r = mid;
                }
            }

            long nowSum = l * n * (l + 1 + m * n - m) / 2;
            if (best > Math.abs(nowSum - (sumAll - nowSum))) {
                best = Math.abs(nowSum - (sumAll - nowSum));
                x = 'V';
                y = (int) l;
            }

            nowSum = (l + 1) * n * (l + 2 + m * n - m) / 2;
            if (best > Math.abs(nowSum - (sumAll - nowSum))) {
                best = Math.abs(nowSum - (sumAll - nowSum));
                x = 'V';
                y = (int) (l + 1);
            }

            l = 0;
            r = n;

            while (l < r - 1) {
                long mid = (l + r) / 2;
                nowSum = (m * m * mid * mid + m * mid) / 2;

                if (nowSum <= sumAll / 2) {
                    l = mid;
                } else {
                    r = mid;
                }
            }

            nowSum = (m * m * l * l + m * l) / 2;
            if (best > Math.abs(nowSum - (sumAll - nowSum))) {
                best = Math.abs(nowSum - (sumAll - nowSum));
                x = 'H';
                y = (int) l;
            }

            nowSum = (m * m * (l + 1) * (l + 1) + m * (l + 1)) / 2;
            if (best > Math.abs(nowSum - (sumAll - nowSum))) {
                best = Math.abs(nowSum - (sumAll - nowSum));
                x = 'H';
                y = (int) (l + 1);
            }

            System.out.println(x + " " + (y + 1));
        }
    }
}
