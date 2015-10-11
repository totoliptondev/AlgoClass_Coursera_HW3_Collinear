/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new PointComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) throw new NullPointerException();

        double slope;
        if (that.y == this.y && that.x == this.x)
            slope = Double.NEGATIVE_INFINITY;
        else if (that.y == this.y)
            slope = 0.0;
        else if (that.x == this.x)
            slope = Double.POSITIVE_INFINITY;
        else
            slope = (double) (this.y - that.y)/(this.x - that.x);
        return slope;
    }
    private class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1, Point p2) {
            Double slope1 = slopeTo(p1);
            Double slope2 = slopeTo(p2);
            return slope1.compareTo(slope2);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null) throw new NullPointerException();

        if      (this.y > that.y)  return  1;
        else if (this.y < that.y)  return -1;
        else if (this.x > that.x)  return  1;
        else if (this.x < that.x)  return -1;
        else                       return  0;

    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1 = new Point(2, 2);
        Point p2 = new Point(2, 1);
        System.out.println(p1.slopeTo(p2));

    }
}
