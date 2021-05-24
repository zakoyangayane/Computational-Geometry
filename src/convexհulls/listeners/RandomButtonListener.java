package convexհulls.listeners;

import convexհulls.main.GraphPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomButtonListener implements ActionListener {
    private GraphPanel graphPanel;

    public RandomButtonListener(GraphPanel gp) {
        graphPanel = gp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graphPanel.addRandomPoint();
    }
}