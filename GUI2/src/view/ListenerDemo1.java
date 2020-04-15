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

/* *******************************************************
 * NOTE: This file contains multiple classes. (3 classes)
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
 *      
 *      Do NOT put multiple separate classes in a single file.
 *      I am just doing this to keep the complete example in a single file.
 */

/**
 * A GUI class which uses a separate ActionListener class.
 * 
 * @author Alan Fowler
 * @version 1.0
 */
class SimpleGUI {
    
    /** The dimensions of the window. */
    private static final Dimension SIZE = new Dimension(220, 90);
    
    /** The Frame for this GUI application.*/
    private final JFrame myFrame;
    
    /** A button on the GUI. */
    private final JButton myButton;
    
    /** Another button on the GUI. */
    private final JButton myButton2;
    
    /**
     *  Initializes the instance fields.
     */
    SimpleGUI() {
        myFrame = new JFrame("Click On Me!");
        myButton = new JButton("A Button");
        myButton2 = new JButton("Another Button");
    }
    
    /**
     *  Sets up and displays the GUI.
     */
    public void start() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final ActionListener listener = new TrivialActionListener();

        myButton.addActionListener(listener);
        myButton2.addActionListener(listener);

        myFrame.add(myButton, BorderLayout.NORTH);
        myFrame.add(myButton2, BorderLayout.SOUTH);
        myFrame.setSize(SIZE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
}





/* **************************************************************
 *      The ActionListener class is next.
 *      
 *      Do NOT put multiple separate classes in a single file.
 *      I am just doing this to keep the complete example in a single file.
 */

/**
 * An action listener that does something trivial.
 * 
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 2.0
 */
class TrivialActionListener implements ActionListener {

    /**
     * Handles an ActionEvent. In a boring way.
     * 
     * @param theEvent The event.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        System.err.println(theEvent);
        // ((JButton) the_event.getSource()).removeActionListener(this);

        // When buttons are disabled, they should also LOOK disabled
        ((JButton) theEvent.getSource()).setEnabled(false);
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
public final class ListenerDemo1 {

    /**
     * A private constructor to inhibit external instantiation.
     */
    private ListenerDemo1() {
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
                new SimpleGUI().start();
            }
        });
    }
}
