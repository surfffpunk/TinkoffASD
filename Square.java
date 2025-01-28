import java.util.Scanner;
import java.util.Locale;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Square {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        BigDecimal C = scanner.nextBigDecimal();
        BigDecimal eps = new BigDecimal("1E-20");
        BigDecimal left = BigDecimal.ZERO, right = C;
        while (right.subtract(left).compareTo(eps) > 0) {
            BigDecimal mid = left.add(right).divide(BigDecimal.valueOf(2), 20, RoundingMode.HALF_UP);
            if (mid.multiply(mid).add(sqrt(mid.add(BigDecimal.ONE))).compareTo(C) <= 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.printf("%.20f\n", left);

    }

    private static BigDecimal sqrt(BigDecimal A) {
        BigDecimal x0 = new BigDecimal("0");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, 20, RoundingMode.HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(BigDecimal.valueOf(2), 20, RoundingMode.HALF_UP);

        }
        return x1;
    }
}
