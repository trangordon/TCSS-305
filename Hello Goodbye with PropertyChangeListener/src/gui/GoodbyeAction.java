/*
 * TCSS 305
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * The Action which occurs when Goodbye is selected.
 * 
 * @author Alan Fowler
 * @version 1.2
 */
public class GoodbyeAction extends AbstractAction {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 5959325849437523778L;
    
    /** The text object to associate with this Action. */
    private final ChangeableText myText;

    /**
     * Construct this Action.
     * 
     * @param theText the text object to associate with this Action.
     */
    public GoodbyeAction(final ChangeableText theText) {
        super("Goodbye");
        myText = theText;
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
        putValue(Action.SELECTED_KEY, true);
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myText.setText((String) getValue(Action.NAME));
    }

}
