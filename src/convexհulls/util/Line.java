package convexհulls.util;

import java.awt.*;

public class Line {
    private Point point1;
    private Point point2;

    public Line(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint1() {
        return point1;
    }

}
