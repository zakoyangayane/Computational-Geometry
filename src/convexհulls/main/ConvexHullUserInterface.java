package convexհulls.main;

import javax.swing.*;
import java.awt.*;

public class ConvexHullUserInterface implements Runnable {

    public static JFrame mainFrame;

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = screenSize.width / 2;
    public static final int HEIGHT = screenSize.height / 2;

    public static JTextPane numCounterPane = new JTextPane();
    private static JTextPane stepDescriptionPane = new JTextPane();

    public static void log(String s) {
        if (stepDescriptionPane != null)
            stepDescriptionPane.setText(s);
    }

    @Override
    public void run() {
        mainFrame = new JFrame("Ուռուցիկ բազմանկյուն");

        mainFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GraphPanel graphPanel = new GraphPanel();

        mainFrame.setJMenuBar(new ConvexHullMenuBar(graphPanel));

        createComponents(mainFrame.getContentPane(), graphPanel);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void createComponents(Container container, GraphPanel graphPanel) {
        mainFrame.setLayout(new GridBagLayout());

        GridBagConstraints constraints;

        ConvexHullToolbar toolbar = new ConvexHullToolbar(graphPanel);
        constraints = getConstraints(0, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL);
        container.add(toolbar, constraints);

        constraints = getConstraints(0, 1, 2, 1, 1, GridBagConstraints.BOTH);
        container.add(graphPanel, constraints);

    }

    private GridBagConstraints getConstraints(int x, int y, int width, int weightx, int weighty, int fill) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
        return c;
    }

    public static void hideTheme() {
        mainFrame.setVisible(false);
    }


}
