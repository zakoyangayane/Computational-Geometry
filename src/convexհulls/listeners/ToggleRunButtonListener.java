package convexհulls.listeners;

import convexհulls.main.GraphPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleRunButtonListener implements ActionListener {
    private final GraphPanel graphPanel;

    public ToggleRunButtonListener(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (graphPanel.isRunning()) {
            graphPanel.stop();
        } else {
            graphPanel.resume();
        }
    }

}
