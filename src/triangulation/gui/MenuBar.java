package triangulation.gui;

import triangulation.main.TriangulationMain;
import triangulation.util.CommandGenerator;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

    private final JFrame frame;
    private final CommandGenerator commandGenerator;

    public MenuBar(JFrame frame) {
        this.frame = frame;

        commandGenerator = CommandGenerator.getInstance();
        addEditMenu();
        addSettingsMenu();
    }

    private void addEditMenu() {

        JMenu editMenu = new JMenu("Խմբագրել");

        JMenuItem undo = new JMenuItem("Չեղարկել");
        undo.addActionListener(e -> commandGenerator.triggerUndo());
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);

        JMenuItem redo = new JMenuItem("Կրկնել");
        redo.addActionListener(e -> commandGenerator.triggerRedo());
        redo.setAccelerator(KeyStroke.getKeyStroke("control X"));
        editMenu.add(redo);

        editMenu.addSeparator();

        JMenuItem reset = new JMenuItem("Ջնջել");
        reset.addActionListener(e -> commandGenerator.triggerClear());
        reset.setAccelerator(KeyStroke.getKeyStroke("R"));
        editMenu.add(reset);

        editMenu.addSeparator();

        JMenuItem addPoints = new JMenuItem("Ավելացնել կետեր...");
        addPoints.addActionListener(e -> commandGenerator.triggerAddPoints());
        addPoints.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(addPoints);

        editMenu.addSeparator();

        JMenuItem triangulate = new JMenuItem("Օգտագործել ալգորիթմը");
        triangulate.addActionListener(e -> commandGenerator.triggerTriangulate());
        triangulate.setAccelerator(KeyStroke.getKeyStroke("T"));
        editMenu.add(triangulate);

        super.add(editMenu);
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
