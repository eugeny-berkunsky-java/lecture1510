package main;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private final double[] x = {100, 200, 200, 100, 250, 300, 350};
    private final double[] y = {100, 100, 200, 200, 250, 300, 250};

    private final int[][] polygons = {
            {0, 1, 2, 3},
            {4, 5, 6}
    };

    public int getPolygonCount() {
        return polygons.length;
    }

    public Polygon2D getPolygon(int k) {
        int n = polygons[k].length;
        List<Point2D> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new Point2D(x[polygons[k][i]], y[polygons[k][i]]));
        }
        return new Polygon2D(points);
    }
}
