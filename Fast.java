import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
public class Fast {
    public static void main(String[] args) {
      In in = new In(args[0]);
      int n = in.readInt();
      // draw the result
      StdDraw.setXscale(0, 32768);
      StdDraw.setYscale(0, 32768);
      StdDraw.setPenRadius(0.005);

      Point[] points = new Point[n];
      // read in all points
      for (int i = 0; i < n; i++)
      {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
      }
      // Use Hashmap to detect slope and the last point in a line
      // if a line has the same slope and last point, then it's been
      // detected already.
      HashMap<Double, HashSet<String>> visited = new HashMap<Double, HashSet<String>>();

      for (int i = 0; i < n-3; i++)
      {
          Arrays.sort(points, i, n);        // start with ith smallest point
          Arrays.sort(points, i+1, n, points[i].SLOPE_ORDER); //sort the remaining point in slope order

          int lo = i+1;
          int hi = i+2;
          while (hi < n) {
            while (hi < n && points[i].SLOPE_ORDER.compare(points[lo], points[hi]) == 0)
            {
              hi++;
            }
            if (hi - lo >= 3) // found 3 or more equal points, print the line
            {
              Arrays.sort(points, lo, hi);
              printline(points, i, lo, hi, visited);
            }
            lo = hi;
          }
      }
    }

    private static void printline(Point[] points, int i, int lo, int hi, HashMap<Double, HashSet<String>> visited)
    {
      double slope = points[i].slopeTo(points[hi-1]);

      // stop if we have seen the line before
      if (visited.get(slope) != null
        && visited.get(slope).contains(points[hi-1].toString()))
        return;

      // print out the point in the correct format, and draw line.
      StdOut.print(points[i]);
      for (int v = lo; v < hi; v++)
      {
        StdOut.print("->" + points[v]);
      }
      StdOut.println();
      points[i].drawTo(points[hi-1]);


      // add the last point and the slope to the visited map.
      HashSet<String> lastPoints = visited.get(slope);
      if (lastPoints == null) lastPoints = new HashSet<String>();
      lastPoints.add(points[hi-1].toString());
      visited.put(slope, lastPoints);

    }
}
