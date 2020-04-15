/*
 * Event Listeners TCSS 305
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* *******************************************************
 * NOTE: This file contains multiple classes. (2 classes)
 * It is generally NOT a good idea to put multiple classes into a single file.
 * I am doing this just to keep the complete example in a single file.
 * 
 * Notice the Checkstyle warning on the package statement which states:
 * "Outer Type Number: Outer types defined is 3 (max allowed is 1)."
 * Checkstyle is reminding me that it is not good to place multiple classes in a single file.
 * 
 * When placing multiple classes in a single file only one class should be 'public'.
 * The 'public' class should have the same name as the file.
 */



/* **************************************************************
 *      The GUI class is listed first.
 *      This time the GUI class also implements ActionListener.
 *      
 *      Do NOT put multiple separate classes in a single file.
 *      I am just doing this to keep the complete example in a single file.
 */

/**
 * A GUI class which implements ActionListener.
 * 
 * @author Alan Fowler
 * @version 1.0
 */
class SimpleGUI2 implements ActionListener {
    
    /** The dimensions of the window. */
    private static final Dimension SIZE = new Dimension(220, 90);
    
    /** The time threshold for clicking too fast. */
    private static final long THRESHOLD = 1000; // 1000ms = 1 second
    
    /** The Frame for this GUI application.*/
    private final JFrame myFrame;
    
    /** A button on the GUI. */
    private final JButton myButton;
    
    /** The two-actions-ago timestamp. */
    private long myTwoActionsAgo;

    /** The one-action-ago timestamp. */
    private long myOneActionAgo;
    
    /**
     *  Initializes the instance fields.
     */
    SimpleGUI2() {
        myFrame = new JFrame("Click On Me!");
        myButton = new JButton("A Button");
    }
    
    /**
     *  Sets up and displays the GUI.
     */
    public void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myButton.addActionListener(this);

        myFrame.add(myButton, BorderLayout.CENTER);
        myFrame.setSize(SIZE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
    
    /**
     * Handles an ActionEvent. In a boring way.
     * 
     * @param theEvent The event.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        System.err.println(theEvent);

        // easter egg time!

        if (theEvent.getWhen() - myTwoActionsAgo < THRESHOLD) {
            JOptionPane.showMessageDialog(null, "You're clicking too fast. Please stop.",
                                          "Click Speed Warning", JOptionPane.WARNING_MESSAGE);
        }

        myTwoActionsAgo = myOneActionAgo;
        myOneActionAgo = theEvent.getWhen();
    }
}



/* ***************************************************************************
 *      The Drive class is next.
 *      
 *      Do NOT put multiple separate classes in a single file.
 *      I am just doing this to keep the complete example in a single file.
 */

/**
 * This program starts a simple ActionListener demo.
 * 
 * @author Alan Fowler acfowler@uw.edu
 * @version 1.2
 */
public final class ListenerDemo2 {

    /**
     * A private constructor to inhibit external instantiation.
     */
    private ListenerDemo2() {
        // do nothing
    }

    /**
     * The start point for the demo program.
     * 
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String[] theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleGUI2().start();
            }
        });
    }
}
