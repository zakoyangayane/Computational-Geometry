package convexհulls.algorithms;

import convexհulls.main.ConvexHullUserInterface;
import convexհulls.util.Line;
import convexհulls.util.Primitives;

import java.awt.*;
import java.util.Comparator;
import java.util.LinkedList;

public class MonotoneChain extends ConvexHullAlgo {

    public MonotoneChain(LinkedList<Point> list) {
        super(list);
        init();
    }

    private LinkedList<Point> upperHull = new LinkedList<>();
    private LinkedList<Point> lowerHull = new LinkedList<>();
    private boolean upperHullIsDone;
    private boolean lowerHullIsDone;
    private Point p, q, r;
    private int i = 0;

    @Override
    public void step() {
        if (upperHullIsDone && lowerHullIsDone) {
            ConvexHullUserInterface.log("Finished!");
            return;
        }
        stepNum++;
        if (!upperHullIsDone) {
            convexHullList = upperHull;
            if (i < 0) {
                ConvexHullUserInterface.log("Finished UpperHull!");
                upperHullIsDone = true;
                convexHullList = upperHull;
                i = 0;
                return;
            }
            if (upperHull.size() >= 2
                    && Primitives.orientation(
                    upperHull.get(upperHull.size() - 1),
                    upperHull.get(upperHull.size() - 2),
                    pointList.get(i)) <= 1) {
                ConvexHullUserInterface.log("Right turn, so we back down");
                p = upperHull.get(upperHull.size() - 1);
                q = upperHull.get(upperHull.size() - 2);
                r = pointList.get(i);
                upperHull.removeLast();
                return;
            } else {
                upperHull.addLast(pointList.get(i));
                r = pointList.get(i);
                i--;
                ConvexHullUserInterface.log("Left turn, so we proceed to add " + r);
                return;
            }
        }
        if (upperHullIsDone && !lowerHullIsDone) {
            if (i >= pointList.size()) {
                lowerHullIsDone = true;
                ConvexHullUserInterface.log("Finished computing lower hull... Merging both hulls");
                if (lowerHull.getFirst() == upperHull.getFirst()) {
                    lowerHull.removeFirst();
                }
                if (lowerHull.getLast() == upperHull.getLast()) {
                    lowerHull.removeLast();
                }
                upperHull.addAll(0, lowerHull);
                convexHullList = upperHull;
                return;
            }
            if (lowerHull.size() >= 2
                    && Primitives.orientation(
                    lowerHull.get(lowerHull.size() - 1),
                    lowerHull.get(lowerHull.size() - 2),
                    pointList.get(i)) <= 1) {
                p = upperHull.get(upperHull.size() - 1);
                q = lowerHull.get(lowerHull.size() - 2);
                r = pointList.get(i);
                lowerHull.removeLast();
                ConvexHullUserInterface.log("Left turn, so we remove last point from LowerHull");
            } else {
                lowerHull.addLast(pointList.get(i));
                i++;
                ConvexHullUserInterface.log("Right turn, so we add the point to lowerHull");
            }
            convexHullList = lowerHull;
        }
    }

    @Override
    public boolean isDone() {
        return upperHullIsDone && lowerHullIsDone;
    }

    @Override
    public Line getCurrentStepLine() {
        return null;
    }

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

    @Override
    public void init() {
        upperHull = new LinkedList<>();
        lowerHull = new LinkedList<>();
        ConvexHullUserInterface.log("Sorting...");
        pointList.sort(new XSortComparator());
        i = pointList.size() - 1;
    }

    private static class XSortComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return (p1.x == p2.x) ? p1.y - p2.y : p1.x - p2.x;
        }
    }

}
