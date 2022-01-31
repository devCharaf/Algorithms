import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    // resizable array for connected (aligned) points
    private final ArrayList<LineSegment> connected = new ArrayList<LineSegment>();


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || hasDuplicateOrNull(points)) throw new IllegalArgumentException();

        int n = points.length;

        Point[] myPoints = points.clone();
        Arrays.sort(myPoints);

        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                double slopePQ = myPoints[p].slopeTo(myPoints[q]);
                for (int r = q + 1; r < n - 1; r++) {
                    double slopePR = myPoints[p].slopeTo(myPoints[r]);
                    if (slopePQ == slopePR) {
                        for (int s = r + 1; s < n; s++) {
                            double slopePS = myPoints[p].slopeTo(myPoints[s]);
                            if (slopePQ == slopePS) {
                                connected.add(new LineSegment(myPoints[p], myPoints[s]));
                            }
                        }
                    }
                }
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
