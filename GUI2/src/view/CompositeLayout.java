/*
 * A demonstration of the use of nested layout managers TCSS 305
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Demonstrates composite layout of a bunch of widgets.
 * 
 * @author Daniel M. Zimmerman
 * @author Alan Fowler - numerous changes and additions
 * @version 1.2
 */
public final class CompositeLayout extends JFrame {

    /** A generated Serialization ID. */
    private static final long serialVersionUID = 2134035361287337794L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    /** The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height;
    
    /** A Factor for scaling the size of the GUI relative to the current screen size. */
    private static final int SCALE = 2;

    /**
     * Constructs a frame to demonstrate composite layout.
     */
    public CompositeLayout() {
        super("Composite Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupComponents();

        //pack();
        // setLocationRelativeTo(null); // attempt to center the GUI on the
        // screen

        /* 
         * Sometimes it is OK to ignore a magic number warning.
         * In this case, the meaning of the code is clear without declaring a constant and
         * the number is only used in this one part of the program.
         * 
         * Usually you should declare a constant, instead of using a hard-coded value, if:
         * 1) a well named constant will make the code easier to understand
         * OR
         * 2) the number is used in multiple places in the code and you would like to
         * provide a single location for potential future modification 
         */
        setSize(SCREEN_WIDTH / SCALE, SCREEN_HEIGHT / SCALE);

        // position the frame in the center of the screen
        // works better than using setLocationRelativeTo(null);
        setLocation(SCREEN_WIDTH / 2 - getWidth() / 2, SCREEN_HEIGHT / 2 - getHeight() / 2);

        setVisible(true);
    }

    /**
     * Sets up the components in this frame.
     */
    private void setupComponents() {
        final JPanel masterPanel = new JPanel(new BorderLayout());
        final JPanel northPanel = new JPanel(new FlowLayout());
        final JPanel southPanel = new JPanel(new GridLayout(0, 2));

        final JButton button1 = new JButton("Button 1");
        final JButton button2 = new JButton("Button 2");
        northPanel.add(button1);
        northPanel.add(button2);

        final JLabel left = new JLabel("Lower Left", SwingConstants.LEFT);
        final JLabel right = new JLabel("Lower Right", SwingConstants.RIGHT);
        southPanel.add(left);
        southPanel.add(right);

        masterPanel.add(northPanel, BorderLayout.NORTH);
        masterPanel.add(new JButton("Center Button"), BorderLayout.CENTER);
        masterPanel.add(southPanel, BorderLayout.SOUTH);

        add(masterPanel, BorderLayout.CENTER);
    }

    /**
     * Does some composite layout and displays it.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompositeLayout();
            }
        });
    }
}
