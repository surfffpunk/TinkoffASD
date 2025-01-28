import java.io.*;
import java.util.*;

public class Transportirovka {
    static int numberOfNodes, numberOfEdges;
    static List<List<int[]>> adjacencyList;

    static int findMinimumTime(int cups) {
        boolean[] visited = new boolean[numberOfNodes + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, -1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentTime = -current[0], currentNode = -current[1];
            visited[currentNode] = true;

            if (currentTime > 1440) {
                return 0;
            }

            if (currentNode == numberOfNodes) {
                return currentTime;
            }

            for (int[] edge : adjacencyList.get(currentNode)) {
                if (edge[2] >= cups && !visited[edge[0]]) {
                    int newTime = currentTime + edge[1];
                    if (newTime <= 1440) {
                        pq.offer(new int[]{-newTime, -edge[0]});
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        numberOfNodes = Integer.parseInt(st.nextToken());
        numberOfEdges = Integer.parseInt(st.nextToken());

        if (numberOfNodes == 1) {
            System.out.println(10000000);
            return;
        }

        adjacencyList = new ArrayList<>(numberOfNodes + 1);
        for (int i = 0; i <= numberOfNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        Set<Integer> uniqueWeights = new HashSet<>();

        for (int i = 0; i < numberOfEdges; ++i) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int cups = (weight - 3000000) / 100;
            if (cups <= 0) {
                continue;
            }

            adjacencyList.get(from).add(new int[]{to, time, cups});
            adjacencyList.get(to).add(new int[]{from, time, cups});
            uniqueWeights.add(cups);
        }

        List<Integer> uniqueWeightsList = new ArrayList<>(uniqueWeights);
        Collections.sort(uniqueWeightsList);

        int left = 0;
        int right = uniqueWeightsList.size() - 1;
        int maxCups = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int minTime = findMinimumTime(uniqueWeightsList.get(mid));

            if (minTime == 0) {
                right = mid - 1;
            } else {
                left = mid + 1;

                if (uniqueWeightsList.get(mid) > maxCups) {
                    maxCups = uniqueWeightsList.get(mid);
                }
            }
        }

        System.out.println(maxCups);
    }
}
