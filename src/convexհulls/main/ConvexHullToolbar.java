package convexհulls.main;

import convexհulls.listeners.ClearButtonListener;
import convexհulls.listeners.RandomButtonListener;
import convexհulls.listeners.ToggleRunButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ConvexHullToolbar extends JToolBar {

    private final int IMAGE_SIZE = 16;


    private final GraphPanel graphPanel;

    public ConvexHullToolbar(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;

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
        addPoints.addActionListener(new RandomButtonListener(graphPanel));
        addPoints.setToolTipText("Պատահական կետեր (Ctrl+A)");

        ImageIcon delete = new ImageIcon(getClass().getResource("/res/img/clear.png"));
        delete.setImage(getResizedImage(delete.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton deletePoints = new JButton(delete);
        super.add(deletePoints);
        deletePoints.addActionListener(new ClearButtonListener(graphPanel));
        deletePoints.setToolTipText("Ջնջել (R)");

    }

    private void addHelpButton() {
        ImageIcon help = new ImageIcon(getClass().getResource("/res/img/help.png"));
        help.setImage(getResizedImage(help.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton helpButton = new JButton(help);
        super.add(helpButton);
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Ծրագրի նպատակն է ստեղծել բազմնանկյուն իր մեջ ներառելով\n" +
                        "տախտակի վրա տրված կետերը․ Այն կարող ենք իրականացնել 3 ալգորիթմներով՝\n" +
                        "1․ Graham Scan,\n" +
                        "2․ Jarvis March,\n" +
                        "3․ Monotone Chain Algorithm․\n" +
                        "Կետերը կարող ենք նշել 2 եղանակով՝\n" +
                        "1․ Սեղմելով տախտակի վրա,\n" +
                        "2․ Սեղմելով <Պատահական կետեր> կոճակի վրա․"
        ));
        helpButton.setToolTipText("Օգնել");
    }

    private void addTriangulationButton() {
        ImageIcon triangulation = new ImageIcon(getClass().getResource("/res/img/triangle.png"));
        triangulation.setImage(getResizedImage(triangulation.getImage(), IMAGE_SIZE, IMAGE_SIZE));
        JButton triangulationButton = new JButton(triangulation);
        super.add(triangulationButton);
        triangulationButton.addActionListener(new ToggleRunButtonListener(graphPanel));
        triangulationButton.setToolTipText("Աշխ․/Կանգ․ (Ctrl+X)");
    }


    private Image getResizedImage(Image src, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();

        g2.drawImage(src, 0, 0, width, height, null);
        g2.dispose();

        return resizedImage;
    }
}
