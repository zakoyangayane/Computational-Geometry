package triangulation.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UserInterface implements Runnable {

    private static JFrame frame;

    @Override
    public void run() {
        File settings = new File("config/settings.ini");
        if (!settings.exists()) {
            String line = "Կարծես առաջին անգամ եք աշխատեցնում ծրագիրը․\n" +
                    "Նշում․ եթե բազմանկյան գագաթները տրամադրված չեն ժամացույցի սլաքի ուղղությամբ, " +
                    "ապա ալգորիթմը կշրջի դրանք․\n" +
                    "Ավելի մանրամասն տեղեկությունների համար, սեղմեք <Օգնել> կոճակը․";
            JOptionPane.showMessageDialog(null, line);
            try {
                settings.getParentFile().mkdirs();
                settings.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        frame = new JFrame("Բազմանկյան եռանկյունացում");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setJMenuBar(new MenuBar(frame));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void hideTheme() {
        frame.setVisible(false);
    }

    private void createComponents(Container container) {
        frame.setLayout(new GridBagLayout());

        DrawingBoard drawingBoard = new DrawingBoard();

        GridBagConstraints constraints;

        Toolbar toolbar = new Toolbar();
        constraints = getConstraints(0, 0, 2, 1, 0, GridBagConstraints.HORIZONTAL);
        container.add(toolbar, constraints);


        PolygonPointCounter pointCountLabel = new PolygonPointCounter(drawingBoard);
        constraints = getConstraints(1, 2, 1, 0, 0, GridBagConstraints.HORIZONTAL);
        container.add(pointCountLabel, constraints);

        constraints = getConstraints(0, 1, 2, 1, 1, GridBagConstraints.BOTH);
        container.add(drawingBoard, constraints);

        drawingBoard.addObserver(pointCountLabel);
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
}
