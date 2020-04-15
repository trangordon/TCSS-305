/*
 * TCSS 305 Assignment 2
 */

package view;

import java.awt.EventQueue;
import utility.FileLoader;

/**
 * The Driver class provides the main method for a simple shopping cart program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes including use of BigDecimal and file input)
 * 
 * @version 2.1
 */

public final class Driver {
    
    /**
     * The path and name of the inventory file.
     */
    private static final String INVENTORY_FILE = "files/tacoma.txt";   

    /**
     * A private constructor, to prevent external instantiation.
     */
    private Driver() {
    }

    /**
     * The main() method - displays the GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) { 
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI(FileLoader.readItemsFromFile(INVENTORY_FILE));
            }
        });
    } // end main()

} // end class
