/**
 * 
 */
package it.unibo.oop.lab.simplegui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class MiniGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public MiniGUI() {
        final JPanel canvas = new JPanel();
        // 1.1 Create a new JPanel
        final JPanel anotherPanel = new JPanel();
        canvas.setLayout(new BorderLayout());

        // 1.2 Use an horizontal BoxLayout as layout
        anotherPanel.setLayout(new BoxLayout(anotherPanel, BoxLayout.X_AXIS));

        // 2.1 Create a new "Result" text field
        final JTextField textField = new JTextField();

        final JButton write = new JButton("Print a random number on standard output");
        //canvas.add(write, BorderLayout.CENTER);
        frame.setContentPane(canvas);

        // 1.3 Set the new JPanel as the only content of the center of the current BorderLayout
        canvas.add(anotherPanel, BorderLayout.CENTER);

        // 1.4 Add the JButton to the new panel
        anotherPanel.add(write);

        // 2.2 Add it to the external JPanel
        canvas.add(textField, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String currentRandom = Integer.toString(rng.nextInt());
                System.out.println(currentRandom);

                // 3.1 The text field displays the same number that gets printed
                textField.setText(currentRandom);
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);

        // 1.6 In display(), use JFrame.pack() to resize the frame 
        //     to the minimum size prior to displaying
        frame.pack();
    }

    /**
     * @param args ignored
     */
    public static void main(final String... args) {
       new MiniGUI().display();
    }

}
