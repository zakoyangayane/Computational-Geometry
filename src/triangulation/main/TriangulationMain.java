package triangulation.main;

import convexհulls.main.ConvexHullUserInterface;
import triangulation.gui.UserInterface;

import javax.swing.*;

public class TriangulationMain {
    public static void main(String... args) {
        if (args.length == 0 || args[0].equals("1")) {
            if (args.length > 0 && args[0].equals("1"))
                ConvexHullUserInterface.hideTheme();
            SwingUtilities.invokeLater(new UserInterface());
        } else {
            UserInterface.hideTheme();
            SwingUtilities.invokeLater(new ConvexHullUserInterface());
        }
    }
}
