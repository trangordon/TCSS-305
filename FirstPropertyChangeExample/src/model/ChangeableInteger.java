/*
 * TCSS 305
 * An example to demonstrate the use of a PropertyChangeListener.
 */

package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import view.GUI;

/**
 * An observable Integer that notifies its observers with its new value whenever
 * its value changes.
 * 
 * @author TCSS 305 instructors
 * @version 1.4
 */
public class ChangeableInteger {
    
    // Instance Fields

    /**
     * The integer value encapsulated by this class.
     */
    private int myValue;

    /**
     * A Random object used by this class to increment or decrement the integer randomly.
     */
    private final Random myRandom = new Random();
    
    /**
     * Support for firing property change events from this class.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    // Instance Methods

    /**
     * Increments the long value by some random long.
     */
    public void incrementRandom() {
        final int temp = myRandom.nextInt(GUI.MAX_CHANGE);
        
        // check if adding temp2 to myValue does not result in an overflow
        if (myValue + temp > myValue) {
            // update myValue and send a notification
            final int oldValue = myValue;
            myValue = myValue + temp;
            myPCS.firePropertyChange("int changed", oldValue, myValue);        
        } 
    }

    /**
     * Decrements the long value by some random long.
     */
    public void decrementRandom() {
        final int temp = myRandom.nextInt(GUI.MAX_CHANGE);
        
        // check if subtracting temp from myValue does not result in an underflow
        if (myValue - temp < myValue) {
            // update myValue and send a notification
            final int oldValue = myValue;
            myValue = myValue - temp;
            myPCS.firePropertyChange("int changed", oldValue, myValue);        
        }
    }
    
    // methods required for PropertyChange support

    /**
     * Adds a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to add.
     */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.addPropertyChangeListener(theListener);
    }
    
    /**
     * Removes a listener for property change events from this class.
     * 
     * @param theListener a PropertyChangeListener to remove.
     */
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPCS.removePropertyChangeListener(theListener);
    }
}
