/*
 * Introduction to Swing - File Choosers TCSS 305
 */

package view;

import javax.swing.JFileChooser;

/**
 * A quick test of a file chooser.
 * 
 * @author Daniel M. Zimmerman
 * @author Alan Fowler - added address examples
 *                       (default, user.home, user.dir, relative, absolute)
 * @version 2.0
 */
public final class FileChooserDemo {

    /**
     * Private constructor to prevent instantiation.
     */
    private FileChooserDemo() {
        // do nothing
    }

    /*
     * This example is a utility class - everything is static. In this example,
     * everything is done in main. This keeps the example simple, but this is
     * NOT how you should code actual projects.
     */
    /**
     * Tests a file dialog.
     * 
     * @param theArgs Command line arguments, ignored.
     */
    public static void main(final String[] theArgs) {

        // Windows default location is the Documents folder
        final JFileChooser fileChooser = new JFileChooser(); 

        /*
         * These next 2 are system dependent and will behave different on various systems:
         *  
         * final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
         * final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
         */

        /*
         * These demonstrate relative addressing (relative to the current directory)
         * 
         * A single dot '.' represents the current directory
         * final JFileChooser fileChooser = new JFileChooser("."); // current directory
         * 
         * double dot ".." represents the parent of the current directory
         * double backslash"\\" represents the root of the current directory
         * which is probably the root directory of the hard drive
         */
        
        // This demonstrates absolute addressing
        // DON'T use absolute addressing to YOUR hard drive! I don't have your hard drive!
        // final JFileChooser fileChooser = new JFileChooser("C:/Test Workspace/GUI 2/images");

        int result = fileChooser.showOpenDialog(null);

        while (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("You didn't choose a file, so file is "
                               + fileChooser.getSelectedFile()
                               + " and you have to choose again.");
            result = fileChooser.showOpenDialog(null);
        }

        final String fileName = fileChooser.getSelectedFile().toString();
        System.out.println("You chose file " + fileName);
    }
}
