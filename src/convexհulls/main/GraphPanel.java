package convexհulls.main;

import convexհulls.algorithms.ConvexHullAlgo;
import convexհulls.algorithms.GrahamScan;
import convexհulls.algorithms.JarvisMarch;
import convexհulls.algorithms.MonotoneChain;
import convexհulls.listeners.ConvexHullMouseListener;
import convexհulls.util.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel {

    public final static int DEFAULT_DELAY_MS = 5;

    private final static int BUFFER_BOUNDS_PX = 5;

    private final LinkedList<Point> pointList;
    private LinkedList<Point> convexHullList;
    private LinkedList<Point> stepPointList;

    private final Random random = new Random();
    private Line step;

    private final CGActionListener cgActionListener = new CGActionListener();
    private final Timer taskTimer = new Timer(DEFAULT_DELAY_MS, cgActionListener);

    private class CGActionListener implements ActionListener {
        private ConvexHullAlgo convexHullAlgo;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!convexHullAlgo.isDone()) {
                int numStep = convexHullAlgo.getCurrentStep();
                ConvexHullUserInterface.numCounterPane.setText(Integer.toString(numStep));
                step = convexHullAlgo.getCurrentStepLine();
                stepPointList = convexHullAlgo.getCurrentStepPoints();
                convexHullAlgo.step();
                setConvexHullList(convexHullAlgo.getConvexHullList());
            } else {
                step = null;
                stepPointList = null;
                stop();
            }
            repaint();
        }

        public void setConvexHullAlgo(ConvexHullAlgo cha) {
            convexHullAlgo = cha;
        }
    }

    public GraphPanel() {
        convexHullList = new LinkedList<>();
        pointList = new LinkedList<>();
        ConvexHullMouseListener mouseListener = new ConvexHullMouseListener(this);
        super.addMouseListener(mouseListener);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ConvexHullUserInterface.WIDTH, ConvexHullUserInterface.HEIGHT);

        for (Point p : pointList) {
            g.setColor(Color.BLACK);
            g.drawRect((int) p.getX() - 1, (int) p.getY() - 1, 2, 2);
        }

        if (step != null) {
            g.setColor(new Color(255, 0, 255));
            g.drawLine(step.getPoint1().x, step.getPoint1().y,
                    step.getPoint2().x, step.getPoint2().y);
        }

        if (stepPointList != null) {
            g.setColor(new Color(0, 255, 0));
            for (int i = 0; i < stepPointList.size() - 1; i++) {
                g.drawLine(stepPointList.get(i).x, stepPointList.get(i).y,
                        stepPointList.get(i + 1).x, stepPointList.get(i + 1).y);
                g.fillOval(stepPointList.get(i).x - 10,
                        stepPointList.get(i).y - 10, 20, 20);
            }
        }

        if (convexHullList != null) {
            g.setColor(new Color(255, 255, 0));
            for (int i = 0; i < convexHullList.size() - 1; i++) {
                g.drawLine(convexHullList.get(i).x, convexHullList.get(i).y,
                        convexHullList.get(i + 1).x,
                        convexHullList.get(i + 1).y);
            }
        }
    }

    public void addRandomPoint() {
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(getWidth() - BUFFER_BOUNDS_PX * 2 + 1)
                    + BUFFER_BOUNDS_PX;
            int y = random.nextInt(getHeight() - BUFFER_BOUNDS_PX * 2 + 1)
                    + BUFFER_BOUNDS_PX;

            Point p = new Point(x, y);
            pointList.add(p);
        }
        repaint();
    }

    public void clearPoints() {
        pointList.clear();
        repaint();
    }

    public void clearStepPoints() {
        stepPointList = null;
        repaint();
    }

    public void clearStep() {
        step = null;
    }

    public void clearConvexHull() {
        convexHullList.clear();
    }

    public void clear() {
        clearConvexHull();
        clearPoints();
        clearStepPoints();
        clearStep();
    }

    public void start(String chAlgorithm) {
        long start;
        long elapsedTime;
        switch (chAlgorithm) {
            case CHAppSettings.CH_JARVIS_MARCH_NAME:
                start = System.nanoTime();
                final JarvisMarch jm = new JarvisMarch(pointList);
                cgActionListener.setConvexHullAlgo(jm);
                elapsedTime = System.nanoTime() - start;
                System.out.println(elapsedTime);
                break;
            case CHAppSettings.CH_MONOTONE_CHAIN_NAME:
                start = System.nanoTime();
                final MonotoneChain mc = new MonotoneChain(pointList);
                cgActionListener.setConvexHullAlgo(mc);
                elapsedTime = System.nanoTime() - start;
                System.out.println(elapsedTime);
                break;
            case CHAppSettings.CH_GRAHAM_SCAN_NAME:
                start = System.nanoTime();
                final GrahamScan gs = new GrahamScan(pointList);
                cgActionListener.setConvexHullAlgo(gs);
                elapsedTime = System.nanoTime() - start;
                System.out.println(elapsedTime);
                break;
            default:
                throw new IllegalStateException("No valid algorithm selected");
        }

        ConvexHullUserInterface.numCounterPane.setText("0");
        taskTimer.start();
    }

    public void stop() {
        taskTimer.stop();
    }

    public void resume() {
        taskTimer.start();
    }

    public void setConvexHullList(LinkedList<Point> newConvexHullList) {
        if (newConvexHullList == null) {
            throw new IllegalArgumentException(
                    "Null convex hull list passed in");
        }
        this.convexHullList = newConvexHullList;
    }

    public void addPoint(int x, int y) {
        pointList.add(new Point(x, y));
        repaint();
    }

    public boolean isRunning() {
        return taskTimer.isRunning();
    }
}
