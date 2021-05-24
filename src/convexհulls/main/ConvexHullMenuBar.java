package convexհulls.main;

import convexհulls.listeners.ClearButtonListener;
import convexհulls.listeners.RandomButtonListener;
import convexհulls.listeners.ToggleRunButtonListener;
import triangulation.main.TriangulationMain;

import javax.swing.*;

public class ConvexHullMenuBar extends JMenuBar {

    private final GraphPanel graphPanel;

    public ConvexHullMenuBar(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        addEditMenu();
        addTriangulationMenu();
        addSettingsMenu();
    }

    private void addEditMenu() {

        JMenu editMenu = new JMenu("Խմբագրել");

        JMenuItem redo = new JMenuItem("Աշխ․/Կանգ․");
        redo.addActionListener(new ToggleRunButtonListener(graphPanel));
        redo.setAccelerator(KeyStroke.getKeyStroke("control X"));
        editMenu.add(redo);

        editMenu.addSeparator();

        JMenuItem reset = new JMenuItem("Ջնջել");
        reset.addActionListener(new ClearButtonListener(graphPanel));
        reset.setAccelerator(KeyStroke.getKeyStroke("R"));
        editMenu.add(reset);

        editMenu.addSeparator();

        JMenuItem addPoints = new JMenuItem("Պատահական կետեր...");
        addPoints.addActionListener(new RandomButtonListener(graphPanel));
        addPoints.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(addPoints);

        super.add(editMenu);
    }

    private void addTriangulationMenu() {
        JMenu triangulationMenu = new JMenu("Օգտագործել ալգորիթմը");

        JMenuItem triangulate1 = new JMenuItem("Graham Scan");
        triangulate1.addActionListener(e -> graphPanel.start("Graham Scan"));
        triangulate1.setAccelerator(KeyStroke.getKeyStroke("control G"));
        triangulationMenu.add(triangulate1);

        JMenuItem triangulate2 = new JMenuItem("Monotone Chain Algorithm");
        triangulate2.addActionListener(e -> graphPanel.start("Monotone Chain Algorithm"));
        triangulate2.setAccelerator(KeyStroke.getKeyStroke("control M"));
        triangulationMenu.add(triangulate2);

        JMenuItem triangulate3 = new JMenuItem("Jarvis March");
        triangulate3.addActionListener(e -> graphPanel.start("Jarvis March"));
        triangulate3.setAccelerator(KeyStroke.getKeyStroke("control J"));
        triangulationMenu.add(triangulate3);

        super.add(triangulationMenu);
    }

    private void addSettingsMenu() {
        JMenu settingsMenu = new JMenu("Կարգավորումներ");

        JMenuItem settings1 = new JMenuItem("Եռանկյունացում");
        JMenuItem settings2 = new JMenuItem("Ուռուցիկ բազմանկյուն");

        settings1.addActionListener(e -> TriangulationMain.main("1"));
        settings1.setAccelerator(KeyStroke.getKeyStroke("M"));
        settingsMenu.add(settings1);

        settings2.addActionListener(e -> TriangulationMain.main("2"));
        settings2.setAccelerator(KeyStroke.getKeyStroke("N"));
        settingsMenu.add(settings2);

        super.add(settingsMenu);
    }

}
