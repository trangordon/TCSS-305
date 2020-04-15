/*
 * TCSS 305
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * The Action which occurs when Hello is selected.
 * 
 * @author Alan Fowler
 * @version 1.2
 */
public class HelloAction extends AbstractAction {

    /** A generated serialization ID. */
    private static final long serialVersionUID = -7054698136312723843L;
    
    /** The text object to associate with this Action. */
    private final ChangeableText myText;

    /**
     * Construct this Action.
     * 
     * @param theText the text object to associate with this Action.
     */
    public HelloAction(final ChangeableText theText) {
        super("Hello");
        myText = theText;
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
        putValue(Action.SELECTED_KEY, true);
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myText.setText((String) getValue(Action.NAME));
    }

}
