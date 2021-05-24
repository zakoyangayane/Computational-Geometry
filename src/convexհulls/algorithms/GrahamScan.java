package convexհulls.algorithms;

import convexհulls.main.ConvexHullUserInterface;
import convexհulls.util.Line;
import convexհulls.util.Primitives;

import java.awt.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class GrahamScan extends ConvexHullAlgo {

    private final LinkedList<Point> stepPointList;

    private int i = 2;

    private boolean isDone;
    private Point lowestRightestPt;

    private Point p, q, r;

    public GrahamScan(LinkedList<Point> list) {
        super(list);
        stepPointList = new LinkedList<>();

        init();
    }

    private static class AngleComparator implements Comparator<Point> {
        private final Point comparisonPoint;

        public AngleComparator(Point cp) {
            comparisonPoint = cp;
        }

        @Override
        public int compare(Point p1, Point p2) {
            if (p1 == comparisonPoint) {
                return 1;
            } else if (p2 == comparisonPoint) {
                return -1;
            }
            double angleOfP1 = Math.atan(Math.abs(p1.getY()
                    - comparisonPoint.getY())
                    / Math.abs(p1.getX() - comparisonPoint.getX()));
            if (p1.getX() < comparisonPoint.getX()) {
                angleOfP1 = Math.PI - angleOfP1;
            }
            double angleOfP2 = Math.atan(Math.abs(p2.getY()
                    - comparisonPoint.getY())
                    / Math.abs(p2.getX() - comparisonPoint.getX()));
            if (p2.getX() < comparisonPoint.getX()) {
                angleOfP2 = Math.PI - angleOfP2;
            }
            if (angleOfP1 > angleOfP2) {
                return 1;
            } else if (angleOfP2 > angleOfP1) {
                return -1;
            } else {
                if (p1.getX() < p2.getX()) {
                    return 1;
                } else if (p2.getX() > p1.getX()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    @Override
    protected void init() {
        Iterator<Point> it = pointList.iterator();
        lowestRightestPt = pointList.getFirst();
        while (it.hasNext()) {
            Point p = it.next();
            if (p.y > lowestRightestPt.y) {
                lowestRightestPt = p;
            } else if (p.y == lowestRightestPt.y) {
                lowestRightestPt = (lowestRightestPt.x > p.x) ? p
                        : lowestRightestPt;
            }
        }
        pointList.sort(new AngleComparator(lowestRightestPt));
        convexHullList.add(lowestRightestPt);
        convexHullList.add(pointList.getFirst());
        convexHullList.add(pointList.get(1));
    }

    @Override
    public void step() {
        if (isDone) {
            ConvexHullUserInterface.log("Finished!");
            return;
        }
        stepNum++;
        if (i >= pointList.size()) {
            isDone = true;
            convexHullList.add(lowestRightestPt);
            pointList.add(lowestRightestPt);
            return;
        }
        p = convexHullList.get(convexHullList.size() - 2);
        q = convexHullList.get(convexHullList.size() - 1);
        r = pointList.get(i);
        if (Primitives.orientation(p, q, r) <= 1 || i == pointList.size() - 1) {
            ConvexHullUserInterface.log("Adding point " + r + " because left turn.");
            convexHullList.add(r);
            i++;
        } else if (Primitives.orientation(p, q, r) == 2) {
            ConvexHullUserInterface.log("Remove point " + q + " because right turn.");
            convexHullList.remove(q);
        }
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public Line getCurrentStepLine() {
        return null;
    }

    @Override
    public LinkedList<Point> getCurrentStepPoints() {
        if (p == null || q == null || r == null) {
            return null;
        }
        stepPointList.clear();
        stepPointList.add(p);
        stepPointList.add(q);
        stepPointList.add(r);
        return stepPointList;
    }
}
