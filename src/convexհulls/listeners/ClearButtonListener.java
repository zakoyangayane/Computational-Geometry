package convexհulls.listeners;

import convexհulls.main.GraphPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {
    private GraphPanel graphPanel;

    public ClearButtonListener(GraphPanel gp) {
        graphPanel = gp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        graphPanel.stop();
        graphPanel.clear();
    }

}