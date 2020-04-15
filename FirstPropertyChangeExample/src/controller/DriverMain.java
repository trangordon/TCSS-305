/*
 * TCSS 305
 * An example to demonstrate the use of a PropertyChangeListener.
 */

package controller;

import java.awt.EventQueue;
import model.ChangeableInteger;
import view.DecrementAction;
import view.GUI;
import view.IncrementAction;

/**
 * Starts the code example.
 * 
 * @author TCSS 305 instructors
 * @version 2.0
 */
public final class DriverMain {
    
    /** Private constructor to inhibit external instantiation. */
    private DriverMain() {
        // do nothing
    }

    /**
     * The start point for the program.
     * 
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String[] theArgs) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // create the ChangeableInteger
                final ChangeableInteger ci = new ChangeableInteger();
                
                // create the GUI and Actions
                final GUI view = new GUI(new IncrementAction(ci),
                                         new DecrementAction(ci));
                // register the GUI to receive PropertyChangeEvents from the ChangeableInteger
                ci.addPropertyChangeListener(view);
                
                view.start(); // start the GUI
            }
        });
    }

}
