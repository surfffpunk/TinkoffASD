import java.util.*;

public class HungryHorse {
    private static final int[] MOVE_X = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] MOVE_Y = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardSize = scanner.nextInt();
        int startX = scanner.nextInt() - 1;
        int startY = scanner.nextInt() - 1;
        int endX = scanner.nextInt() - 1;
        int endY = scanner.nextInt() - 1;
        scanner.close();

        int[][] distance = new int[boardSize][boardSize];
        int[][][] prev = new int[boardSize][boardSize][2];
        for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
        distance[startX][startY] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentX = currentCell[0], currentY = currentCell[1];

            for (int i = 0; i < 8; i++) {
                int nextX = currentX + MOVE_X[i], nextY = currentY + MOVE_Y[i];
                if (nextX >= 0 && nextY >= 0 && nextX < boardSize && nextY < boardSize && distance[nextX][nextY] == Integer.MAX_VALUE) {
                    distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    prev[nextX][nextY] = new int[]{currentX, currentY};
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        System.out.println(distance[endX][endY]);
        List<int[]> path = new ArrayList<>();
        for (int x = endX, y = endY; x != startX || y != startY; ) {
            path.add(new int[]{x + 1, y + 1});
            int px = prev[x][y][0], py = prev[x][y][1];
            x = px;
            y = py;
        }
        path.add(new int[]{startX + 1, startY + 1});

        Collections.reverse(path);
        for (int[] cell : path) {
            System.out.println(cell[0] + " " + cell[1]);
        }
    }
}
