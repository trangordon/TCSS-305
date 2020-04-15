package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Runs the program by instantiating and starting the GUI.
 * 
 * @author Gordon Tran
 * @version November 2018
 */
public final class Driver {
    /**
     * Private constructor.
     */
    private Driver() {
    }

    /**
     * The main method, starts the GUI.
     * 
     * @param theArgs
     *            Command line arguments.
     */
    public static void main(final String[] theArgs) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final ClassNotFoundException e) {
        } catch (final InstantiationException e) {
        } catch (final IllegalAccessException e) {
        } catch (final UnsupportedLookAndFeelException e) {
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().start();
            }
        });
    }
}
