/*
 * TCSS 305
 */

package gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * An object to encapsulate text and to report changes to that text.
 * 
 * @author Alan Fowler
 * @version 1.0
 */
public class ChangeableText {
    
    /** The text to encapsulate. */
    private String myText;
    
    /**
     * Support for firing property change events from this class.
     */
    private final PropertyChangeSupport myPCS = new PropertyChangeSupport(this);
    
    /**
     * Initializes the object.
     * 
     * @param theIntialText the text to use to initialize this object
     */
    public ChangeableText(final String theIntialText) {
        myText = theIntialText;
    }
    
    /**
     * Sets the text to the provided String and reports the change.
     * 
     * @param theText the new text
     */
    public void setText(final String theText) {
        if (!myText.equals(theText)) {
            myPCS.firePropertyChange("text", myText, theText);
            myText = theText;
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
