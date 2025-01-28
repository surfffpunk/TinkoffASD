import java.util.*;

public class Olympics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTasks = scanner.nextInt();
        long pointsPerTask = scanner.nextLong();

        List<Task> tasks = new ArrayList<>();
        for (long i = 1; i <= numTasks; i++) {
            long startTime = scanner.nextLong();
            long duration = scanner.nextLong();
            tasks.add(new Task(startTime, duration, pointsPerTask, i));
        }

        tasks.sort(Comparator.comparingLong(task -> task.endTime));

        long totalPoints = 0;
        long currentTime = 0;
        List<Long> selectedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.startTime >= currentTime) {
                totalPoints += pointsPerTask;
                currentTime = task.endTime;
                selectedTasks.add(task.index);
            }
        }

        System.out.println(totalPoints);
        System.out.println(selectedTasks.size());
        for (long index : selectedTasks) {
            System.out.print(index + " ");
        }
    }
}

class Task {
    long startTime;
    long endTime;
    long points;
    long index;

    Task(long startTime, long duration, long points, long index) {
        this.startTime = startTime;
        this.endTime = startTime + duration;
        this.points = points;
        this.index = index;
    }
}
