import java.util.*;
import java.io.*;

public class OfflineSeminar {
    private static final int INFINITY = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] cityAndRoadCounts = parseInputLine(reader.readLine());
        int cityCount = cityAndRoadCounts[0];
        int roadCount = cityAndRoadCounts[1];

        int[][] distances = initializeDistances(cityCount);
        for(int i = 0; i < roadCount; i++) {
            int[] roadInfo = parseInputLine(reader.readLine());
            int city1 = roadInfo[0] - 1;
            int city2 = roadInfo[1] - 1;
            int distance = roadInfo[2];
            distances[city1][city2] = distance;
            distances[city2][city1] = distance;
        }

        applyFloydWarshallAlgorithm(distances, cityCount);
        int optimalCity = findOptimalCity(distances, cityCount);
        System.out.println(optimalCity + 1);
    }

    private static int[] parseInputLine(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[][] initializeDistances(int cityCount) {
        int[][] distances = new int[cityCount][cityCount];
        for(int i = 0; i < cityCount; i++) {
            Arrays.fill(distances[i], INFINITY);
            distances[i][i] = 0;
        }
        return distances;
    }

    private static void applyFloydWarshallAlgorithm(int[][] distances, int cityCount) {
        for(int k = 0; k < cityCount; k++)
            for(int i = 0; i < cityCount; i++)
                for(int j = 0; j < cityCount; j++)
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
    }

    private static int findOptimalCity(int[][] distances, int cityCount) {
        int minMaxDistance = INFINITY;
        int optimalCity = -1;
        for(int i = 0; i < cityCount; i++) {
            int maxDistance = 0;
            for(int j = 0; j < cityCount; j++)
                if(i != j)
                    maxDistance = Math.max(maxDistance, distances[i][j]);
            if(maxDistance < minMaxDistance) {
                minMaxDistance = maxDistance;
                optimalCity = i;
            }
        }
        return optimalCity;
    }
}
