import java.util.*;

public class Cashbox {
    static class Time implements Comparable<Time> {
        long hour, minute, second;

        public Time(long hour, long minute, long second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        @Override
        public int compareTo(Time other) {
            if (hour != other.hour) return Long.compare(hour, other.hour);
            if (minute != other.minute) return Long.compare(minute, other.minute);
            return Long.compare(second, other.second);
        }
    }

    static long convertToSeconds(Time t) {
        return t.hour * 3600 + t.minute * 60 + t.second;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Time> timings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Time openingTime = new Time(scanner.nextLong(), scanner.nextLong(), scanner.nextLong());
            Time closingTime = new Time(scanner.nextLong(), scanner.nextLong(), scanner.nextLong());
            timings.add(openingTime);
            timings.add(closingTime);
        }

        timings.sort(Time::compareTo);

        int count = 0;
        long totalOverlap = 0;
        long currentTime = 0;

        for (int i = 0; i < timings.size(); i++) {
            Time time = timings.get(i);
            long timeInSeconds = convertToSeconds(time);

            totalOverlap += (timeInSeconds - currentTime) * count;
            currentTime = timeInSeconds;

            if (i % 2 == 0) {
                count++;
            } else {
                count--;
            }
        }

        System.out.println(totalOverlap);
    }
}
