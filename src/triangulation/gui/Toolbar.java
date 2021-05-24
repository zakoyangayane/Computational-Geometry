package triangulation.gui;

import triangulation.util.CommandGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar {

    private final int IMAGE_SIZE = 16;
    private final CommandGenerator commandGenerator;

    public Toolbar() {
        commandGenerator = CommandGenerator.getInstance();

        super.setFloatable(false);

        addEditButtons();
        super.addSeparator();

        addTriangulationButton();
        super.addSeparator();

        super.add(Box.createHorizontalGlue());
        addHelpButton();
    }

    private void addEditButtons() {
        ImageIcon add = new ImageIcon(getClass().getResource("/res/img/add.png"));
        add.setImage(getResizedImage(add.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton addPoints = new JButton(add);
        super.add(addPoints);
        addPoints.addActionListener(e -> commandGenerator.triggerAddPoints());
        addPoints.setToolTipText("Ավելացնել կետեր (Ctrl+A)");

        ImageIcon delete = new ImageIcon(getClass().getResource("/res/img/clear.png"));
        delete.setImage(getResizedImage(delete.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton deletePoints = new JButton(delete);
        super.add(deletePoints);
        deletePoints.addActionListener(e -> commandGenerator.triggerClear());
        deletePoints.setToolTipText("Ջնջել (R)");

    }

    private void addHelpButton() {
        ImageIcon help = new ImageIcon(getClass().getResource("/res/img/help.png"));
        help.setImage(getResizedImage(help.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton helpButton = new JButton(help);
        super.add(helpButton);
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Այս ծրագրի նպատակն է ցույց տալ բազմանկյան եռանկյունների փոփոխությունը կետեր ավելացնելիս․\n" +
                        "Կա կետեր ավելացնելու երկու եղանակ՝\n" +
                        "1․ Կարող եք սեղմել տախտակին յուրաքանչյուր կետն ավելացնելու համար,\n" +
                        "2․ Կարող եք օգտագործել \"Ավելացնել կետեր (Ctrl+A)\" կոճակը․\n" +
                        "Նշում․ եթե բազմանկյան գագաթները տրամադրված չեն ժամացույցի սլաքի ուղղությամբ, " +
                        "ապա ալգորիթմը կշրջի դրանք.\n"
        ));
        helpButton.setToolTipText("Օգնել");
    }

    private void addTriangulationButton() {
        ImageIcon triangulation = new ImageIcon(getClass().getResource("/res/img/triangle.png"));
        triangulation.setImage(getResizedImage(triangulation.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton triangulationButton = new JButton(triangulation);
        super.add(triangulationButton);
        triangulationButton.addActionListener(e -> commandGenerator.triggerTriangulate());
        triangulationButton.setToolTipText("Օգտագործել ալգորիթմը");
    }


    private Image getResizedImage(Image src, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();

        g2.drawImage(src, 0, 0, width, height, null);
        g2.dispose();

        return resizedImage;
    }
}
