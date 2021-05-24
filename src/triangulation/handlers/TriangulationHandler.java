package triangulation.handlers;

import triangulation.util.Polygon2D;
import triangulation.util.Triangulator;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

public class TriangulationHandler extends SwingWorker<Void, Void> {

    private Triangulator triangulator;
    private Polygon2D polygon;
    private List<List<Point2D.Double>> triangulation;

    public TriangulationHandler(Triangulator triangulator) {
        this.triangulator = triangulator;
        triangulation = null;
    }

    private boolean isOrderedCounterclockwise(Polygon2D polygon) {
        Iterator<Point2D.Double> it = polygon.getPointsList().iterator();

        Point2D.Double previous = it.next();
        Point2D.Double current;

        double result = 0;
        while (it.hasNext()) {
            current = it.next();

            result += previous.getX() * current.getY() - current.getX() * previous.getY();
            previous = current;
        }

        it = polygon.getPointsList().iterator();
        current = it.next();
        result += previous.getX() * current.getY() - current.getX() * previous.getY();

        return result > 0;
    }

    public void triangulate(Polygon2D polygon) throws Exception {
        if (triangulator == null)
            throw new Exception("Triangulator not set.");

        if (polygon.getPointsCount() < 3)
            throw new Exception("Առնվազն 3 կետ պետք է տրված լինի եռանկյունացումը սկսելուց առաջ.");

        if (!isOrderedCounterclockwise(polygon))
            polygon.reversePointsList();

        this.polygon = polygon;

        this.execute();
    }

    @Override
    protected Void doInBackground() {
        triangulation = triangulator.getTriangulation(polygon);

        return null;
    }

    @Override
    protected void done() {
        polygon.setTriangulation(triangulation);
    }

}