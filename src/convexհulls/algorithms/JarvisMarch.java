package convexհulls.algorithms;

import convexհulls.main.ConvexHullUserInterface;
import convexհulls.util.Line;
import convexհulls.util.Primitives;

import java.awt.*;
import java.util.LinkedList;

public class JarvisMarch extends ConvexHullAlgo {

    public JarvisMarch(LinkedList<Point> list) {
        super(list);
        init();
    }

    private int currentIndexOfElement = 0;
    private Point p, q, r;

    private boolean isDone = false;

    protected void init() {
        convexHullList = new LinkedList<>();

        ConvexHullUserInterface.log("Finding leftmost point...");
        Point leftMostPoint = getLeftmostPoint(pointList);
        convexHullList.add(leftMostPoint);

        p = leftMostPoint;
        q = pointList.getFirst();
        r = pointList.getFirst();
    }

    @Override
    public void step() {
        if (isDone) {
            ConvexHullUserInterface.log("Finished");
            return;
        }
        stepNum++;
        if (currentIndexOfElement >= pointList.size()) {
            convexHullList.add(q);
            if (q == convexHullList.getFirst()) {
                isDone = true;
                r = null;
            }
            p = q;
            q = pointList.getFirst();
            currentIndexOfElement = 0;
            ConvexHullUserInterface.log("Resetting index search based on new point " + p);
            return;
        }
        r = pointList.get(currentIndexOfElement);
        if (q == p || Primitives.orientation(p, q, r) == 1) {
            ConvexHullUserInterface.log("Left turn! so we move up from q");
            q = r;
        } else {
            ConvexHullUserInterface.log("Right turn!");
        }
        currentIndexOfElement++;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    public LinkedList<Point> getConvexHullList() {
        return convexHullList;
    }

    private Point getLeftmostPoint(LinkedList<Point> list) {
        Point leftMostPoint = list.getFirst();
        for (Point p : list) {
            if (p.x < leftMostPoint.x) {
                leftMostPoint = p;
            }
        }
        return leftMostPoint;
    }

    public Line getCurrentStepLine() {
        return null;
    }

    @Override
    public LinkedList<Point> getCurrentStepPoints() {
        if (p == null || q == null || r == null) {
            return null;
        }
        LinkedList<Point> points = new LinkedList<>();
        points.add(p);
        points.add(q);
        points.add(r);
        points.add(p);
        return points;
    }
}
