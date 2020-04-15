/*
 * TCSS 305
 * An example to demonstrate the use of a PropertyChangeListener.
 */

package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.ChangeableInteger;

/**
 * An action class for the increment action.
 * 
 * @author TCSS 305 instructors
 * @version 1.4
 */
public class IncrementAction extends AbstractAction {

    /** A version ID for serialization. */
    private static final long serialVersionUID = -7183235794902380073L;
 
    /** The integer to be incremented. */
    private final ChangeableInteger myInt;

    /**
     * Constructs a new IncrementAction.
     * 
     * @param theInt The integer to be incremented.
     */
    public IncrementAction(final ChangeableInteger theInt) {
        super("Increment");
        myInt = theInt;
    }

    /**
     * The method called when the action is performed.
     * 
     * @param theEvent The event that caused the action to be performed.
     */
    public void actionPerformed(final ActionEvent theEvent) {
        myInt.incrementRandom();
    }
}
