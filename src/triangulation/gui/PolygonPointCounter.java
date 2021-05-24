package triangulation.gui;

import triangulation.util.Observer;
import triangulation.util.ObserverConstants;

import javax.swing.*;

@SuppressWarnings("serial")
public class PolygonPointCounter extends JLabel implements Observer {

    private final DrawingBoard drawingBoard;

    public PolygonPointCounter(DrawingBoard drawingBoard) {
        this.drawingBoard = drawingBoard;
    }

    @Override
    public void update(int state) {
        if (state == ObserverConstants.DRAWBOARD_REPAINT)
            super.setText("Կետերի քանակը: " + drawingBoard.getPolygon().getPointsCount() + " ");
    }
}
