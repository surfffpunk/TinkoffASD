import java.io.*;
import java.util.*;

public class NextElement {
    static TreeSet<Integer> set = new TreeSet<>();
    static int lastAnswer = 0;
    static boolean isLastOperationQuery = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            if (operation.equals("+")) {
                if (isLastOperationQuery) {
                    set.add((x + lastAnswer) % (int)1e9);
                } else {
                    set.add(x);
                }
                isLastOperationQuery = false;
            } else {
                Integer answer = set.ceiling(x);
                if (answer == null) {
                    lastAnswer = 0;
                    pw.println(-1);
                } else {
                    lastAnswer = answer;
                    pw.println(answer);
                }
                isLastOperationQuery = true;
            }
        }
        pw.close();
    }
}
