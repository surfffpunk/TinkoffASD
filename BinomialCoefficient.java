import java.io.*;

public class BinomialCoefficient {
    static final int MOD = 1000000007;
    static long[] factorial;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        factorial = new long[n+1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = (factorial[i-1] * i) % MOD;
        }

        long result = calculateBinomialCoefficient(n, k);
        System.out.println(result);
    }

    static long calculateBinomialCoefficient(int n, int k) {
        long numerator = factorial[n];
        long denominator = (factorial[k] * factorial[n-k]) % MOD;
        return (numerator * calculateModInverse(denominator, MOD)) % MOD;
    }

    static long calculateModInverse(long a, int m) {
        long m0 = m;
        long y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            long quotient = a / m;
            long temp = m;

            m = (int) (a % m);
            a = temp;
            temp = y;

            y = x - quotient * y;
            x = temp;
        }

        if (x < 0)
            x += m0;

        return x;
    }
}
