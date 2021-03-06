package triangulation.util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.*;

public class Polygon2D implements Subject {

    private final static double RADIUS = 6;

    private final List<Observer> observers;

    private final List<Point2D.Double> points;
    private final List<Line2D.Double> edges;
    private final Deque<Point2D.Double> redoPoints;
    private final Deque<Line2D.Double> redoEdges;
    private final HashSet<Point2D.Double> pointsHash;

    private List<List<Point2D.Double>> triangles;
    private boolean triangulationInProgress;

    public Polygon2D() {
        observers = new ArrayList<>();
        points = new LinkedList<>();
        edges = new LinkedList<>();
        redoPoints = new ArrayDeque<>();
        redoEdges = new ArrayDeque<>();
        pointsHash = new HashSet<>();

        triangles = null;
        triangulationInProgress = false;
    }

    public void addPoint(Point2D.Double point) {
        if (pointsHash.contains(point))
            return;

        if (points.size() >= 1)
            edges.add(new Line2D.Double(points.get(points.size() - 1), point));

        points.add(point);
        pointsHash.add(point);
        triangles = null;

        notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);

        redoPoints.clear();
        redoEdges.clear();
    }

    public void addPoint(double x, double y) {
        Point2D.Double point = new Point2D.Double(x, y);
        addPoint(point);
    }

    public void removeLast() {
        if (!points.isEmpty()) {
            int lastPointIndex = points.size() - 1;
            int lastEdgeIndex = edges.size() - 1;

            pointsHash.remove(points.get(lastPointIndex));
            redoPoints.push(points.remove(lastPointIndex));
            if (!edges.isEmpty())
                redoEdges.push(edges.remove(lastEdgeIndex));

            triangles = null;
            notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
        }

    }

    public void restoreLast() {
        if (!redoPoints.isEmpty()) {
            points.add(redoPoints.pop());

            if (points.size() > 1)
                edges.add(redoEdges.pop());

            triangles = null;
            notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
        }

    }

    public void clear() {
        while (!points.isEmpty())
            removeLast();
        pointsHash.clear();
        triangles = null;

        notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
    }

    public void reversePointsList() {
        ListIterator<Point2D.Double> it = points.listIterator(points.size());
        List<Point2D.Double> newList = new ArrayList<>();

        while (it.hasPrevious())
            newList.add(it.previous());

        clear();
        for (Point2D.Double point : newList)
            addPoint(point);
    }

    public List<Point2D.Double> getPointsList() {
        return points;
    }

    public int getPointsCount() {
        return points.size();
    }

    public boolean isTriangulated() {
        return triangles != null;
    }

    public boolean isTriangulating() {
        return triangulationInProgress;
    }

    public void setTriangulationInProgress() {
        triangulationInProgress = true;
    }

    public void setTriangulation(List<List<Point2D.Double>> triangles) {
        this.triangles = triangles;
        triangulationInProgress = false;
        notifyObservers(ObserverConstants.DRAWBOARD_REPAINT);
    }

    public void draw(Graphics2D g) {
        for (Line2D.Double edge : edges)
            g.draw(edge);

        for (Point2D.Double point : points)
            g.fill(new Ellipse2D.Double(point.getX() - RADIUS / 2,
                    point.getY() - RADIUS / 2, RADIUS, RADIUS));

        if (points.size() >= 3) {
            int lastIndex = points.size() - 1;
            g.draw(new Line2D.Double(points.get(lastIndex), points.get(0)));
        }

        drawDiagonals(g);
    }

    private void drawDiagonals(Graphics2D g) {
        if (triangles == null)
            return;

        Color lastColor = g.getColor();
        g.setColor(Color.RED);

        Iterator<List<Point2D.Double>> it = triangles.iterator();
        List<Point2D.Double> triangle = it.next();

        while (it.hasNext()) {
            g.draw(new Line2D.Double(triangle.get(0).getX(), triangle.get(0).getY(),
                    triangle.get(2).getX(), triangle.get(2).getY()));
            triangle = it.next();
        }

        g.setColor(lastColor);
    }

    @Override
    public void addObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void removeObserver(Observer ob) {
        int index = observers.indexOf(ob);
        if (index != -1)
            observers.remove(index);
    }

    @Override
    public void notifyObservers(int state) {
        for (Observer ob : observers)
            ob.update(state);
    }
}