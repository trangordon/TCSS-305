/*
 * TCSS 305
 * An example to demonstrate the use of a PropertyChangeListener.
 */

package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a GUI with two buttons and an int value; one button increases the
 * value by a random amount, the other decreases it. This demonstrates use of
 * PropertyChangeListener.
 * 
 * Integer.MAX_VALUE =  2147483647
 * Integer.MIN_VALUE = -2147483648
 * 
 * @author TCSS 305 instructors
 * @version 1.4
 */
public final class GUI extends JPanel implements PropertyChangeListener {

    /** The maximum amount for an increment or decrement operation. */
    public static final int MAX_CHANGE = 100000000;
    
    
    /** A generated version ID for Serialization. */
    private static final long serialVersionUID = 3864867617156082245L;
    
    /** The width of the window. */
    private static final int WIDTH = 800;

    /** The height of the window. */
    private static final int HEIGHT = 100;
    
    /** A formatter for numbers. */
    private static final NumberFormat NF = NumberFormat.getIntegerInstance();

    /** The label containing the displayed long. */
    private final JLabel myLabel;

    /** An array of Actions to associate with buttons. */
    private final Action[] myActions;

    // Constructor

    /**
     * Constructs a new IntGUI.
     * 
     * @param theActions an array of Actions to associate with buttons in the GUI.
     */
    public GUI(final Action... theActions) {
        super();
        myLabel = new JLabel("0");
        myActions = theActions;
    }
    
    /** Displays the GUI. */
    public void start() {
        add(new JButton(myActions[1])); // decrement
        add(myLabel);
        add(new JButton(myActions[0])); // increment
        
        final JFrame frame =
            new JFrame("Update by a random value 0 to " + NF.format(MAX_CHANGE));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.add(this);
        
        final JPanel infoPanel = new JPanel();
        final JLabel minLabel = new JLabel("min: " + NF.format(Integer.MIN_VALUE));
        final JLabel maxLabel = new JLabel("max: " + NF.format(Integer.MAX_VALUE));
        infoPanel.add(minLabel);
        infoPanel.add(maxLabel);
        frame.add(infoPanel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }

    // Interface Methods

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (theEvent.getPropertyName().equals("int changed")) {
            myLabel.setText(NF.format(theEvent.getNewValue()));
            repaint(); // this indirectly calls paintComponent() to redraw the GUI
        }
    }

}
