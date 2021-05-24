package triangulation.util;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CommandGenerator {

    private static final CommandGenerator commandGenerator = new CommandGenerator();
    private static Robot robot;

    private CommandGenerator() {
    }

    public static CommandGenerator getInstance() {
        if (robot == null)
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

        return commandGenerator;
    }

    public void triggerUndo() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_Z);
    }

    public void triggerRedo() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_X);
    }

    public void triggerClear() {
        robot.keyPress(KeyEvent.VK_R);
        robot.keyRelease(KeyEvent.VK_R);
    }

    public void triggerAddPoints() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
    }

    public void triggerTriangulate() {
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_T);
    }

}
