/*Gordon Tran 
 * Assignment 4
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.Filter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;

/**
 * The Class GUI.
 *
 * @author Gordon Tran
 * @version October 2018
 */
public class GUI {
    
//    /**Button Check.*/
//    private boolean myButtonCheck = true;
    
    /** JFrame field. */
    private final JFrame myFrame = new JFrame("Assignment 4");
    
    /** The panel for the filter buttons. */
    private final JPanel myPanel1 = new JPanel(new GridLayout(0, 1));
    
    /** The my panel for the bottom menu. */
    private final JPanel myPanel2 = new JPanel();
    
    /** The my panel for the image. */
    private final JPanel myPanel3 = new JPanel(new GridBagLayout());
    
    /** The label. */
    private final JLabel myLabel1 = new JLabel();
    
    /**The image.*/
    private PixelImage myImage;

    /**The file chooser.*/
    private final JFileChooser myChoose = new JFileChooser("sample_images");
    
    /** The file. */
    private File myFile = myChoose.getSelectedFile();

    /** The boolean for enabled buttons.*/
    private boolean myEnable;
    
    //Filter Fields
    /** Edge Detector Filter.*/
    private final EdgeDetectFilter myEdgeDF = new EdgeDetectFilter();
    /** Edge HighlightFilter.*/
    private final EdgeHighlightFilter myEdgeHF = new EdgeHighlightFilter();
    /** Flip Horizontal Filter.*/
    private final FlipHorizontalFilter myFlipHF = new FlipHorizontalFilter();
    /** Flip Vertical Filter.*/
    private final FlipVerticalFilter myFlipVF = new FlipVerticalFilter();
    /** Grayscale Filter.*/
    private final GrayscaleFilter myGrayF = new GrayscaleFilter();
    /** Sharpen Filter.*/
    private final SharpenFilter mySharpF = new SharpenFilter();
    /** Soften Filter.*/
    private final SoftenFilter mySoftF = new SoftenFilter();
    
    //Button Fields
    /**Edge Detector button.*/
    private final JButton myEdgeD = buttonMake("Edge Detect", myEdgeDF);
    /**Edge Highlight button.*/
    private final JButton myEdgeH = buttonMake("Edge Highlight", myEdgeHF);
    /**Flip Horizontal button.*/
    private final JButton myFlipH = buttonMake("Flip Horizontal", myFlipHF);
    /**Flip Vertical button.*/
    private final JButton myFlipV = buttonMake("Flip Vertical", myFlipVF);
    /**Gray scale button.*/
    private final JButton myGray = buttonMake("Grayscale", myGrayF);
    /**Sharpen button.*/
    private final JButton mySharp = buttonMake("Sharpen", mySharpF);
    /**Soften button.*/
    private final JButton mySoft = buttonMake("Soften", mySoftF);
    /**Open button.*/
    private final JButton myOpen = buttonMake("Open...", new Open());
    /**Save button.*/
    private final JButton mySave = buttonMake("Save as...", new Save());
    /**Close button.*/
    private final JButton myClose = buttonMake("Close Image", new Close());
    
    /**
     * Start.
     */
    public void start() {
        //Starting frame stuff
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //filter buttons
        myPanel1.add(myEdgeD);
        myPanel1.add(myEdgeH);
        myPanel1.add(myFlipH);
        myPanel1.add(myFlipV);
        myPanel1.add(myGray);
        myPanel1.add(mySharp);
        myPanel1.add(mySoft);
//        if (myButtonCheck) {
//            final JButton edgeD = buttonMake("Edge Detect", myEdgeDF);
//            final JButton edgeH = buttonMake("Edge Highlight", myEdgeHF);
//            final JButton flipH = buttonMake("Flip Horizontal", myFlipHF);
//            final JButton flipV = buttonMake("Flip Vertical", myFlipVF);
//            final JButton gray = buttonMake("Grayscale", myGrayF);
//            final JButton sharp = buttonMake("Sharpen", mySharpF);
//            final JButton soft = buttonMake("Soften", mySoftF);
//            myPanel1.add(edgeD);
//            myPanel1.add(edgeH);
//            myPanel1.add(flipH);
//            myPanel1.add(flipV);
//            myPanel1.add(gray);
//            myPanel1.add(sharp);
//            myPanel1.add(soft);
//            edgeD.setEnabled(myEnable);  
//            edgeH.setEnabled(myEnable);
//            flipH.setEnabled(myEnable);
//            flipV.setEnabled(myEnable);
//            gray.setEnabled(myEnable);
//            sharp.setEnabled(myEnable);
//            soft.setEnabled(myEnable);
//        }
        myFrame.add(myPanel1, BorderLayout.WEST);
        
        //the bottom buttons
        myPanel2.add(myOpen);
        myPanel2.add(mySave);
        myPanel2.add(myClose);
//        final JButton open = buttonMake("Open", new Open());
//        open.setEnabled(true);
//        if (myButtonCheck) {
//            final JButton save = buttonMake("Save as", new Save());
//            final JButton close = buttonMake("Close Image", new Close());
//            myPanel2.add(open);
//            myPanel2.add(save);
//            myPanel2.add(close);
//            save.setEnabled(myEnable);
//            close.setEnabled(myEnable);
//        }
        myOpen.setEnabled(true);
        myFrame.add(myPanel2, BorderLayout.SOUTH);
        buttonEnable();
//        buttonEnable(edgeD, edgeH, flipH, flipV, gray, sharp, soft, save, close);
        
        //image panel
        myPanel3.add(myLabel1);
        myFrame.add(myPanel3, BorderLayout.CENTER);
        
        //End sizing stuff
        myFrame.setMinimumSize(new Dimension(0, 0));
        myFrame.pack();
        myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), myFrame.getHeight()));
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
//        myButtonCheck = false;
    }
    
    /**
     * Button maker.
     *
     * @param theName the name of the button
     * @param theListener the listener
     * @return the Jbutton
     */
    private JButton buttonMake(final String theName, final ActionListener theListener) {
        final JButton button = new JButton(theName);
        button.addActionListener(theListener);
        button.setEnabled(myEnable);
        return button;
    }
    

    /**
     * Button maker.
     *
     * @param theName the name of the button
     * @param theFilter the filter
     * @return the Jbutton
     */
    private JButton buttonMake(final String theName, final Filter theFilter) {
        /**Makes the action listener for filters.*/
        class ActionFilter implements ActionListener {

            /** ({@inheritDoc}.*/
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                theFilter.filter(myImage); 
                myLabel1.setIcon(new ImageIcon(myImage));
            }
        }
        final JButton button = new JButton(theName);
        button.addActionListener(new ActionFilter());
        button.setEnabled(myEnable);
        return button;
    }
    
    /**
     * Makes the buttons enabled or disabled.
     * Based on myEnable variable.  
     */
    private void buttonEnable() {
        myEdgeD.setEnabled(myEnable);  
        myEdgeH.setEnabled(myEnable);
        myFlipH.setEnabled(myEnable);
        myFlipV.setEnabled(myEnable);
        myGray.setEnabled(myEnable);
        mySharp.setEnabled(myEnable);
        mySoft.setEnabled(myEnable);
        mySave.setEnabled(myEnable);
        myClose.setEnabled(myEnable);
    }

//     /**
//     * Makes the buttons enabled or disabled.
//     * Based on myEnable variable.
//     *
//     * @param theEdgeD the edge D
//     * @param theEdgeH the edge H
//     * @param theFlipH the flip H
//     * @param theFlipV the flip V
//     * @param theGray the gray
//     * @param theSharp the sharp
//     * @param theSoft the soft
//     * @param theSave the save
//     * @param theClose the close
//     */
//    private void buttonEnable(final JButton theEdgeD, final JButton theEdgeH,
//                              final JButton theFlipH, final JButton theFlipV, 
//                              final JButton theGray, final JButton theSharp, 
//                              final JButton theSoft, final JButton theSave, 
//                              final JButton theClose) {
//        theEdgeD.setEnabled(myEnable);  
//        theEdgeH.setEnabled(myEnable);
//        theFlipH.setEnabled(myEnable);
//        theFlipV.setEnabled(myEnable);
//        theGray.setEnabled(myEnable);
//        theSharp.setEnabled(myEnable);
//        theSoft.setEnabled(myEnable);
//        theSave.setEnabled(myEnable);
//        theClose.setEnabled(myEnable);
//    }
    
//    /**
//     * The edge detector button action.
//     */
//    class EdgeD implements ActionListener {
//
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            myEdgeDF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The edge highlight button action.
//     */
//    class EdgeH implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            myEdgeHF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The horizontal flip button action.
//     */
//    class FlipH implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            myFlipHF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The vertical flip button action.
//     */
//    class FlipV implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            myFlipVF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The grayscale button action.
//     */
//    class Gray implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            myGrayF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The sharpen button action.
//     */
//    class Sharpen implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            mySharpF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    
//
//    /**
//     * The soften button action.
//     */
//    class Soften implements ActionListener {
//        
//        /** ({@inheritDoc}.*/
//        @Override
//        public void actionPerformed(final ActionEvent theEvent) {
//            mySoftF.filter(myImage); 
//            myLabel1.setIcon(new ImageIcon(myImage));
//        }
//    }    

    /**
     * The open button action.
     */
    class Open implements ActionListener {
        
        /** ({@inheritDoc}.*/
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            final int result = myChoose.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    myFile = myChoose.getSelectedFile();
                    myImage = PixelImage.load(myFile);
                    myChoose.setCurrentDirectory(myFile);
                    myLabel1.setIcon(new ImageIcon(myImage));
                    myFrame.setMinimumSize(new Dimension(0, 0));
                    myFrame.pack();
                    myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), 
                                                         myFrame.getHeight()));
                    myFrame.setLocationRelativeTo(null);
                    myEnable = true;
                    buttonEnable();
//                    start();
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(myFrame,
                                                  "Error: Failed to load a image file.", 
                                                  myFrame.getTitle(),
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }    

    /**
     * The save button action.
     */
    class Save implements ActionListener {
        
        /** ({@inheritDoc}.*/
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            final int result = myChoose.showSaveDialog(null);
            try {
                if (result == JFileChooser.APPROVE_OPTION) {
                    if (myFile.equals(myChoose.getSelectedFile())) {
                        final String warn = "The file \"" + myFile.getName() 
                            + "\" already exists.\nDo you want to replace it?";
                        final String name = myFrame.getTitle();
                        final int option = JOptionPane.showConfirmDialog(myFrame, warn, name,
                                                                     JOptionPane.YES_NO_OPTION,
                                                                  JOptionPane.WARNING_MESSAGE);
                        if (option == JOptionPane.NO_OPTION) {
                            return;
                        }
                    }
                    myImage.save(myChoose.getSelectedFile());
                    myFile = myChoose.getSelectedFile();
                }
            } catch (final IOException e) {
                JOptionPane.showMessageDialog(myFrame, "Error: Could not save image file.",
                                              myFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }    

    /**
     * The close button action.
     */
    class Close implements ActionListener {
        
        /** ({@inheritDoc}.*/
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myLabel1.setIcon(null);
            myFrame.setMinimumSize(new Dimension(0, 0));
            myFrame.pack();
            myFrame.setMinimumSize(new Dimension(myFrame.getWidth(), 
                                                 myFrame.getHeight()));
            myFrame.setLocationRelativeTo(null);
            myEnable = false;
//            start();
            buttonEnable();
        }
    }
}
