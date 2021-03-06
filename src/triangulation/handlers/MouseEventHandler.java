package triangulation.handlers;

import triangulation.gui.DrawingBoard;

import java.awt.event.*;
import java.awt.geom.Point2D;

public class MouseEventHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

    private DrawingBoard drawingBoard;
    private MouseEvent lastPress;

    private double currentTranslateX;
    private double currentTranslateY;

    private Point2D.Double getPointOnBoard(MouseEvent e) {
        Point2D.Double point = new Point2D.Double();
        double x = (e.getX() - drawingBoard.getWidth() / 2 - drawingBoard.getTranslateX()) / drawingBoard.getScaleX();
        double y = (drawingBoard.getHeight() / 2 - e.getY() + drawingBoard.getTranslateY()) / drawingBoard.getScaleY();
        point.setLocation(x, y);
        return point;
    }

    public MouseEventHandler(DrawingBoard drawingBoard) {
        this.drawingBoard = drawingBoard;
        currentTranslateX = currentTranslateY = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawingBoard.getPolygon().addPoint(getPointOnBoard(e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPress = e;

        currentTranslateX = drawingBoard.getTranslateX();
        currentTranslateY = drawingBoard.getTranslateY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawingBoard.setTranslateX(currentTranslateX + e.getX() - lastPress.getX());
        drawingBoard.setTranslateY(currentTranslateY + e.getY() - lastPress.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawingBoard.setTranslateX(currentTranslateX + e.getX() - lastPress.getX());
        drawingBoard.setTranslateY(currentTranslateY + e.getY() - lastPress.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        drawingBoard.setCursorPosition(getPointOnBoard(e));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0)
            drawingBoard.incrementScale();
        else
            drawingBoard.decrementScale();
    }

}
