import java.util.Arrays;
public class Brute {
    public static void main(String[] args) {
      In in = new In(args[0]);
      int n = in.readInt();
      // draw the result
      StdDraw.setXscale(0, 32768);
      StdDraw.setYscale(0, 32768);
      StdDraw.setPenRadius(0.02);
      Point[] points = new Point[n];

      for (int i = 0; i < n; i++)
      {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
      }

      Arrays.sort(points);
      // quadruple loops, note the index of the loops to ensure
      // there are at least 4 points.
      for (int i = 0; i < n-3; i++)
        for (int j = i+1; j < n-2; j++)
          for (int k = j+1; k < n-1; k++)
            for (int v = k+1; v < n; v++)
              {
                double slope = points[i].slopeTo(points[k]);
                if (slope == points[i].slopeTo(points[j])
                  && slope == points[i].slopeTo(points[v]))
                {
                     StdOut.println(points[i] + "->" + points[j] + "->"
                        + points[k] + "->" + points[v]);
                     points[i].drawTo(points[v]);
                }
              }
    }
}
