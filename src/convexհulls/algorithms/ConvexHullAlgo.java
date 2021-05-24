package convexհulls.algorithms;

import convexհulls.util.Line;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public abstract class ConvexHullAlgo {
    protected LinkedList<Point> pointList;
    protected LinkedList<Point> convexHullList;
    protected int stepNum;

    public ConvexHullAlgo(LinkedList<Point> list) {
        convexHullList = new LinkedList<>();
        if (list == null) {
            throw new IllegalArgumentException("Passed in list was null");
        }
        pointList = list;

        if (pointList.size() < 3) {
            JOptionPane.showMessageDialog(null,
                    "Ձախողվեց․․․ կետերի քանակը պետք է լինի 3 կամ ավելին",
                    "Սխալ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected abstract void init();

    public abstract void step();

    public abstract boolean isDone();

    public int getCurrentStep() {
        return stepNum;
    }

    public LinkedList<Point> getConvexHullList() {
        return convexHullList;
    }

    public abstract Line getCurrentStepLine();

    public abstract LinkedList<Point> getCurrentStepPoints();
}
