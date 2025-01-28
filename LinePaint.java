import java.util.*;

public class LinePaint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }

        System.out.println(calculatePaintedLength(segments));
    }

    public static long calculatePaintedLength(List<Segment> segments) {
        segments.sort(Comparator.comparingInt(Segment::getStart));

        long totalLength = 0;
        int currentStart = Integer.MIN_VALUE;
        int currentEnd = Integer.MIN_VALUE;

        for (Segment segment : segments) {
            int start = segment.getStart();
            int end = segment.getEnd();

            if (start > currentEnd) {
                totalLength += currentEnd - currentStart;
                currentStart = start;
                currentEnd = end;
            } else {
                currentEnd = Math.max(currentEnd, end);
            }
        }

        totalLength += currentEnd - currentStart;

        return totalLength;
    }

    static class Segment {
        private final int start;
        private final int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
