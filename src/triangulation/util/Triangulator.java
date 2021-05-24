package triangulation.util;

import java.awt.geom.Point2D;
import java.util.List;

public interface Triangulator {
    List<List<Point2D.Double>> getTriangulation(Polygon2D polygon);
}
