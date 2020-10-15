package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class View {
    private final Canvas canvas;

    private double cx = 0;
    private double cy = 0;

    private double[][] matrix = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
    };

    public void moveFigure(double dx, double dy) {
        double[][] t = {
                {1,0,dx},
                {0,1,dy},
                {0,0,1}
        };
        matrix = multiply(t,matrix);
    }

    public void rotateFigure(double angle) {
        moveFigure(-cx,-cy);
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));
        double[][] t = {
                {cos, -sin, 0},
                {sin, cos, 0},
                {0, 0, 1}
        };
        matrix = multiply(t, matrix);
        moveFigure(cx, cy);
    }

    public void scaleFigure(double s) {
        moveFigure(-cx, -cy);
        double[][] t = {
                {s, 0, 0},
                {0, s, 0},
                {0, 0, 1}
        };
        matrix = multiply(t, matrix);
        moveFigure(cx,cy);
    }

    public void draw(Figure figure) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.BLACK);
        for (int i = 0; i < figure.getPolygonCount(); i++) {
            drawPolygon(figure.getPolygon(i));
        }

        gc.setStroke(Color.BLUE);
        gc.strokeRect(cx-1,cy-1,3,3);
    }

    private void drawPolygon(Polygon2D polygon) {
        List<Point2D> list = polygon.getPoints()
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
        double[] x = list.stream().mapToDouble(Point2D::getX).toArray();
        double[] y = list.stream().mapToDouble(Point2D::getY).toArray();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokePolygon(x, y, x.length);
    }

    private double fx(double x, double y) {
        return matrix[0][0] * x + matrix[0][1] * y + matrix[0][2];
    }

    private double fy(double x, double y) {
        return matrix[1][0] * x + matrix[1][1] * y + matrix[1][2];
    }


    private Point2D transform(Point2D point) {
        double xs = fx(point.getX(), point.getY());
        double ys = fy(point.getX(), point.getY());
        return new Point2D(xs,ys);
    }

    private double[][] multiply(double[][] a, double[][] b) {
        double[][] result = new double[3][3];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public void setCxCy(double x, double y) {
        cx = x;
        cy = y;
    }
}
