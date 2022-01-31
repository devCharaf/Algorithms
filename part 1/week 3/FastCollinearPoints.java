import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> connected = new ArrayList<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null || hasDuplicateOrNull(points)) throw new IllegalArgumentException();

        Point[] myPoints = points.clone();
        Arrays.sort(myPoints);

        for (Point p: points) {
            if (myPoints.length < 4) {
                continue;
            }

            Arrays.sort(myPoints, p.slopeOrder());

            int begin = 1;
            int end = 1;
            double last = p.slopeTo(myPoints[end]);

            for (int j = 2; j < myPoints.length; j++) {
                double slope = p.slopeTo(myPoints[j]);
                if (Double.compare(last, slope) != 0) {
                    if (end - begin >= 2) {
                        // make it unique
                        if (p.compareTo(myPoints[begin]) < 0) {
                            connected.add(new LineSegment(p, myPoints[end]));
                        }
                    }
                    last = slope;
                    begin = j;
                }
                end = j;
            }

        }
    }

    private boolean hasDuplicateOrNull(Point[] points) {
        Point[] myPoints = points.clone();
        Arrays.sort(myPoints);
        for (int i = 0; i < myPoints.length - 1; i++) {
            if (myPoints[i].compareTo(myPoints[i + 1]) == 0 || myPoints[i] == null) return true;
        }
        return points[myPoints.length - 1] == null;
    }

    // the number of line segments
    public int numberOfSegments() {
        return connected.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return connected.toArray(new LineSegment[connected.size()]);
    }

    public static void main(String[] args) {
        /* EMPTY MAIN */
    }
}
