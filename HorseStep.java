import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HorseStep {
    static int[][] dp;

    static void updateMatrix(int i, int j){
        dp[i][j] += dp[i-2][j-1] + dp[i-2][j+1] + dp[i-1][j-2] + dp[i+1][j-2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        n += 2;
        m += 2;
        dp = new int[n+1][m+1];
        dp[2][2] = 1;
        int i = 2, j = 2;
        while( i != n-1 || j != m-1 ){
            int x = i, y = j;
            while(x >= 2 && y < m)
                updateMatrix(x--, y++);
            if(i == n-1) {
                j++;
            } else {
                i++;
            }
        }
        updateMatrix(n-1, m-1);

        System.out.println(dp[n-1][m-1]);
    }
}
