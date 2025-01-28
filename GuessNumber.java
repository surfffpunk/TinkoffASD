import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GuessNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        int n = Integer.parseInt(reader.readLine());
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            writer.println(mid);
            writer.flush();
            String response = reader.readLine();
            if (response.equals("<")) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        writer.println("! " + left);
        writer.flush();
    }
}
