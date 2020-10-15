package main;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Polygon2D {
    @Getter
    private final List<Point2D> points;
}
