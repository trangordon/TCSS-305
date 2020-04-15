/*
 * TCSS 305
 * 
 * This example listens for PropertyChange events!
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * The JMenuBar for this GUI example.
 * 
 * @author Alan Fowler
 * @version 1.2
 */
public class GUIMenuBar extends JMenuBar implements PropertyChangeListener {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 4981470964207567582L;

    /** The View menu. */
    private final JMenu myViewMenu;

    /** A button group for radio buttons. */
    private final ButtonGroup myGroup;

    /** A button to exit the program. */
    private final JMenuItem myQuitButton;

    /**
     * Construct the menu bar.
     * 
     * @param theFrame the JFrame which will contain this JMenuBar
     */
    public GUIMenuBar(final JFrame theFrame) {
        super();
        
        myViewMenu = new JMenu("View");
        myGroup = new ButtonGroup();
        myQuitButton = new JMenuItem("Quit");
        
        setup(theFrame);
    }

    /**
     * Sets up the components of the menu.
     * 
     * @param theFrame the JFrame containing this menu.
     */
    private void setup(final JFrame theFrame) {
        
        final JMenu homeMenu = new JMenu("Home");
        homeMenu.setMnemonic(KeyEvent.VK_O);
        
        myViewMenu.setMnemonic(KeyEvent.VK_V);

        // setup the Quit button
        myQuitButton.setEnabled(false);
        myQuitButton.setMnemonic(KeyEvent.VK_Q);
        myQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
            }
        });

        homeMenu.add(myQuitButton);
        add(homeMenu);
        add(myViewMenu);
    }

    /**
     * Creates a radio button menu item, associates an action with the button,
     * adds the button to a button group, adds the button to the View menu.
     * 
     * @param theAction the Action to associate with the new button being created
     */
    public void createMenuButton(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);

        myGroup.add(createdButton);
        myViewMenu.add(createdButton);
    }
    
    // Here is the handler for property change events
 
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("text".equals(theEvent.getPropertyName())) {
            
            // OK, let's enable the quit button based on the state of the panel's text
            myQuitButton.setEnabled("Goodbye".equals(theEvent.getNewValue()));
        }
    }

}
