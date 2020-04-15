/*
 * TCSS 305
 * An example to demonstrate the use of a PropertyChangeListener.
 */

package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.ChangeableInteger;

/**
 * An action class for the decrement action.
 * 
 * @author TCSS 305 instructors
 * @version 1.4
 */
public class DecrementAction extends AbstractAction {
    
    /** A generated version ID for Serialization. */
    private static final long serialVersionUID = -4569301000506226603L;
    
    /** The integer to be decremented. */
    private final ChangeableInteger myInt;

    /**
     * Constructs a new DecrementAction.
     * 
     * @param theInt The integer to be decremented.
     */
    public DecrementAction(final ChangeableInteger theInt) {
        super("Decrement");
        myInt = theInt;
    }

    /**
     * The method called when the action is performed.
     * 
     * @param theEvent The event that caused the action to be performed.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        myInt.decrementRandom();
    }
}
