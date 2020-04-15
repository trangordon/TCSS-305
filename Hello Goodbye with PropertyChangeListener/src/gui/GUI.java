/*
 * TCSS 305
 */

package gui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JFrame;

/**
 * The Graphical User Interface for this example program.
 * 
 * @author Alan Fowler
 * @version 1.2
 */
public class GUI implements PropertyChangeListener {

    /** The Window for this GUI. */
    private final JFrame myFrame;

    /**
     * Construct the GUI.
     */
    public GUI() {
        myFrame = new JFrame("ToolBar and MenuBar Example with PropertyChangeListener");
        setup();
    }

    /**
     * Sets up the GUI.
     */
    private void setup() {
        
        final GUIMenuBar menuBar = new GUIMenuBar(myFrame);
        final GUIToolBar toolBar = new GUIToolBar();
        final PaintPanel panel = new PaintPanel();
        final ChangeableText text = new ChangeableText("Welcome");
        
        // register the panel as a listener for property changes from the text
        text.addPropertyChangeListener(panel);
        
        // register the menu as a listener for property changes from the text
        text.addPropertyChangeListener(menuBar);
        
        // register this GUI class as listener for property changes from the text
        text.addPropertyChangeListener(this);

        final Action[] actions = {new HelloAction(text), new GoodbyeAction(text)};

        for (final Action current : actions) {
            menuBar.createMenuButton(current);
            toolBar.createToggleButton(current);
        }

        myFrame.setJMenuBar(menuBar);

        myFrame.add(toolBar, BorderLayout.SOUTH);
        myFrame.add(panel, BorderLayout.CENTER);
        
        // This disables the standard exit button on the Frame
        myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        
        if ("text".equals(theEvent.getPropertyName())) {
            
            // OK, let's enable the frame's exit button based on the state of the panel's text
            if ("Goodbye".equals(theEvent.getNewValue())) {
                myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
        
    }

}
