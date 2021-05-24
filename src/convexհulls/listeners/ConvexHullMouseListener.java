package convexհulls.listeners;

import convexհulls.main.GraphPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConvexHullMouseListener implements MouseListener {

    private final GraphPanel graphPanel;

    public ConvexHullMouseListener(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        graphPanel.addPoint(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
