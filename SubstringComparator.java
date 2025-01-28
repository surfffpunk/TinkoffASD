import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubstringComparator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();
        int queryCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < queryCount; i++) {
            String[] query = reader.readLine().split(" ");
            int startA = Integer.parseInt(query[0]) - 1;
            int endA = Integer.parseInt(query[1]) - 1;
            int startB = Integer.parseInt(query[2]) - 1;
            int endB = Integer.parseInt(query[3]) - 1;

            if (areSubstringsEqual(inputString, startA, endA, startB, endB))
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    private static boolean areSubstringsEqual(String inputString, int startA, int endA, int startB, int endB) {
        String substringA = inputString.substring(startA, endA + 1);
        String substringB = inputString.substring(startB, endB + 1);
        return substringA.equals(substringB);
    }
}
