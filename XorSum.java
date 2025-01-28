import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class XorSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] a = new long[n];
        long[] prefixSum = new long[n + 1];
        long[] prefixXor = new long[n + 1];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            a[i] = Long.parseLong(input[i]);
            prefixSum[i + 1] = prefixSum[i] + a[i];
            prefixXor[i + 1] = prefixXor[i] ^ a[i];
        }

        int m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            input = br.readLine().split(" ");
            int q = Integer.parseInt(input[0]);
            int l = Integer.parseInt(input[1]);
            int r = Integer.parseInt(input[2]);
            if (q == 1) {
                System.out.println(prefixSum[r] - prefixSum[l - 1]);
            } else {
                System.out.println(prefixXor[r] ^ prefixXor[l - 1]);
            }
        }
    }
}
