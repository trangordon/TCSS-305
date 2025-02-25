/*
 * TCSS 305 - Assignment 4
 */

package gui;

import java.awt.EventQueue;

/**
 * Runs the program by instantiating and starting the GUI.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.4
 */
public final class Driver {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private Driver() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the GUI. Command line arguments are ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().start();
            }
        });
    }
}
