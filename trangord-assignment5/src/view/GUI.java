/* Gordon Tran
 * Assignment 5
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import model.DrawPanel;

/**
 * The Class GUI.
 * @author Gordon Tran
 * @version November 2018
 */
public class GUI {
    
    /** The scale of the window to the screen.*/
    private static final int SCREEN_MOD = 3;
    
    /** Name of tool pencil.*/
    private static final String P_NAME = "Pencil";
    /** Name of tool line.*/
    private static final String L_NAME = "Line";
    /** Name of tool rectangle.*/
    private static final String R_NAME = "Rectangle";
    /** Name of tool ellipse.*/
    private static final String E_NAME = "Ellipse";
    /**Color button name.*/
    private static final String C_NAME = "Color...";
    /** Name of extension.*/
    private static final String EXT = ".gif";
    /**Max value of thickness.*/
    private static final int S_MAX = 15;
    /**Initial value of thickness.*/
    private static final int S_START = 5;
    
    /** The frame. */
    private JFrame myFrame = new JFrame();
    
    /** The screen size. */
    private Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /** The image. */
    private ImageIcon myImage = new ImageIcon("paint_icon.png");

    /** The panel. */
    private DrawPanel myPanel = new DrawPanel();
    
    /** The toolbar. */
    private JToolBar myToolbar = new JToolBar();
    
    /**Color button.*/
    private JButton myToolColor = new JButton(C_NAME);
    
    /** The pencil. */
    private JToggleButton myPencil = makeButton(P_NAME);
    
    /** The line. */
    private JToggleButton myLine = makeButton(L_NAME);
    
    /** The rectangle. */
    private JToggleButton myRectangle = makeButton(R_NAME);
    
    /** The ellipse. */
    private JToggleButton myEllipse = makeButton(E_NAME);

    /** The tool group. */
    private ButtonGroup myToolGroup = new ButtonGroup();
    
    /** The menu group. */
    private ButtonGroup myMenuGroup = new ButtonGroup();

    /** The menu bar. */
    private JMenuBar myMenuBar = new JMenuBar();
    
    /** The options. */
    private JMenu myOptions = new JMenu("Options");
    
    /**Thickness option.*/
    private JMenu myThick = new JMenu("Thickness");
    
    /**Slider.*/
    private JSlider mySlide = new JSlider(SwingConstants.HORIZONTAL, 0, S_MAX, S_START);
    
    /**Color option.*/
    private JMenuItem myColor = new JMenuItem(C_NAME);
    
    /**Clear option.*/
    private JMenuItem myClear = new JMenuItem("Clear");

    /** The tools. */
    private JMenu myTools = new JMenu("Tools");
    
    /** The R pencil. */
    private JRadioButtonMenuItem myRPencil = makeRButton(P_NAME);
    
    /** The R line. */
    private JRadioButtonMenuItem myRLine = makeRButton(L_NAME);
    
    /** The R rectangle. */
    private JRadioButtonMenuItem myRRectangle = makeRButton(R_NAME);
    
    /** The R ellipse. */
    private JRadioButtonMenuItem myREllipse = makeRButton(E_NAME);

    /** The help. */
    private JMenu myHelp = new JMenu("Help");
    
    /** The about. */
    private JMenuItem myAbout = new JMenuItem("About...");

    /**
     * Start.
     */
    public final void start() {
        myFrame.setTitle("TCSS 305 - Paint Program");
        //Button groups
        myToolGroup.add(myPencil);
        myToolGroup.add(myLine);
        myToolGroup.add(myRectangle);
        myToolGroup.add(myEllipse);
        myLine.setSelected(true);

        myMenuGroup.add(myRPencil);
        myMenuGroup.add(myRLine);
        myMenuGroup.add(myRRectangle);
        myMenuGroup.add(myREllipse);
        myRLine.setSelected(true);
        //Frame setup
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setIconImage(myImage.getImage());
        myPanel.setBackground(Color.WHITE);
        myPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        //Toolbar buttons
        myToolbar.add(myToolColor);
        myToolColor.addActionListener(new ColorC());
        myToolbar.add(myPencil);
        myToolbar.add(myLine);
        myToolbar.add(myRectangle);
        myToolbar.add(myEllipse);
        //Options
        myOptions.add(myThick);
        myThick.add(mySlide);
        mySlide.setPaintTicks(true);
        mySlide.setMinorTickSpacing(1);
        mySlide.setPaintLabels(true);
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(Integer.valueOf(0), new JLabel("0"));
        labelTable.put(Integer.valueOf(S_MAX), new JLabel("15"));
        mySlide.setLabelTable(labelTable);
        mySlide.setFont(new Font("Serif", Font.ITALIC, S_MAX));

//        mySlide.setLabelTable
        myOptions.add(myColor);
        myColor.addActionListener(new ColorC());
        myOptions.add(myClear);
        //Menu buttons
        myTools.add(myRPencil);
        myTools.add(myRLine);
        myTools.add(myRRectangle);
        myTools.add(myREllipse);
        //Help
        myHelp.add(myAbout);
        myAbout.addActionListener(new AboutPane());
        //Menu tabs
        myMenuBar.add(myOptions);
        myMenuBar.add(myTools);
        myMenuBar.add(myHelp);
        //Add elements
        myFrame.add(myPanel, BorderLayout.CENTER);
        myFrame.add(myToolbar, BorderLayout.SOUTH);
        myFrame.setJMenuBar(myMenuBar);
        //Frame end setup
        myClear.setEnabled(false);
        myFrame.setSize(myScreenSize.width / SCREEN_MOD, myScreenSize.height / SCREEN_MOD);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }

    /**
     * Make button method.
     *
     * @param theName the name
//     * @param theListener the listener
     * @return the J button
     */
    private JToggleButton makeButton(final String theName) {
        final JToggleButton btn = new JToggleButton(theName, 
                new ImageIcon(theName.toLowerCase() + EXT));
//        btn.addMouseMotionListener(theListener);
        return btn;
    }

    /**
     * Make radio button method.
     *
     * @param theName the name
//     * @param theListener the listener
     * @return the J radio button
     */
    private JRadioButtonMenuItem makeRButton(final String theName) {
        final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(theName,
                new ImageIcon(theName.toLowerCase() + EXT));
//        btn.addMouseMotionListener(theListener);
        return btn;
    }

    /**
     * About window listener.
     */
    class AboutPane implements ActionListener {
        
        /** The about text. */
        private String myAboutText = "Autumn 2018\nGordon Tran";

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            JOptionPane.showMessageDialog(myFrame, myAboutText, 
                    myFrame.getTitle(), JOptionPane.PLAIN_MESSAGE, myImage);
        }
    }
    /**
     * Color chooser listener.
     */
    class ColorC implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            final Color newC = JColorChooser.showDialog(null, myFrame.getTitle(),
                    myPanel.getMyHue());
            myPanel.setMyHue(newC);
        }
    }
}
