/*Gordon Tran
 * TCSS 305
 * Assignment 6
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javafx.scene.media.AudioClip;
import model.Board;


/**
 * The Class GUI.
 * @author Gordon Tran
 * @version November 2018
 */
public class GUI implements PropertyChangeListener {
//Constants    
    /**Original speed.*/
    private static final int OG_SPEED = 1000;
    
    /**Speed modifier.*/
    private static final int SPEED_CHANGE = 50;
    
    /**Score modifier.*/
    private static final int SCORE_CHANGE = 5;
    
    /**Font size.*/
    private static final float SML_FONT = 8.0f;
    
    /**Font size.*/
    private static final float MED_FONT = 10.0f;
    
    /**Font size.*/
    private static final float LRG_FONT = 12.0f;
    
    /**Font size.*/
    private static final float LRG_PAUSE = 64.0f;
    
    /**Font size.*/
    private static final float MED_PAUSE = 48.0f;
    
    /**Font size.*/
    private static final float SML_PAUSE = 32.0f;
//Frame fields    
    /** The my frame. */
    private final JFrame myFrame = new JFrame("Tetris");
    
    /** The my board. */
    private final Board myBoard = new Board();
    
    /** The my panel. */
    private final JPanel myPanel = new JPanel(new GridLayout(4, 1));
    
    /**Controls.*/
    private final JPanel myControl = new JPanel(new GridLayout(0, 1));
    
    /**Score.*/
    private final JPanel mySScore = new JPanel(new GridLayout(0, 1));
    
    /**Game Panel.*/
    private final JPanel myGameS = new JPanel(new GridLayout(0, 1));
    
    /**New game button.*/
    private final JButton myNewB = makeButton("New Game");
    
    /**End game button.*/
    private final JButton myEndB = makeButton("End Game");
    
    /**About button.*/
    private final JButton myAbout = makeButton("Info");
    
    /**Grid option button.*/
    private final JToggleButton myGrid = makeTButton("Grid");
    
    /**Mute option button.*/
    private final JToggleButton myMute = makeTButton("Mute");
    
    /**Score string.*/
    private final JLabel myScoreT = new JLabel();
    
    /**Level string.*/
    private final JLabel myLevelT = new JLabel();
    
    /** The pause label. */
    private final JLabel myPText = new JLabel();
    
    /** The line text. */
    private final JLabel myLText = new JLabel();
    
    /** The next level. */
    private final JLabel myNLine = new JLabel();
    
    /** The timer. */
    private Timer myTimer;
    
    /**Menu Bar.*/
    private final JMenuBar myBar = new JMenuBar();
    
    /**Game option.*/
    private final JMenu myGame = new JMenu("Game");
    
    /**Start.*/
    private final JMenuItem myStart = new JMenuItem("New Game");
    
    /**End.*/
    private final JMenuItem myEnd = new JMenuItem("End Game");
    
    /**Size option.*/
    private final JMenu mySizeS = new JMenu("Size");
    
    /**Large button.*/
    private final JRadioButtonMenuItem myLargB = makeMButton("Large");
    
    /**Medium button.*/
    private final JRadioButtonMenuItem myMedB = makeMButton("Medium");
    
    /**Small button.*/
    private final JRadioButtonMenuItem mySmlB = makeMButton("Small");
    
    /**Pause indicator.*/
    private boolean myPause;
    
    /**In game.*/
    private boolean myInGame;
    
    /**Tetris Panel.*/
    private final BoardPanel myBPanel = new BoardPanel();
    
    /**Next piece.*/
    private final NextPanel myNPiece = new NextPanel();
    
    /**Game speed.*/
    private int mySpeed = OG_SPEED;
    
    /**Game score.*/
    private int myScore;
    
    /**Level.*/
    private int myLevel;
    
    /**Lines cleared.*/
    private int myLines;
//Size    
    /**Large size.*/
    private final Dimension myLarge = new Dimension(461, 659);
    
    /**Medium size.*/
    private final Dimension myMed = new Dimension(364, 467);
    
    /**Small size.*/
    private final Dimension mySmall = new Dimension(305, 350);
//Audio    
    /**Sounds on or off.*/
    private boolean mySound = true;
    
    /**Lose sounds.*/
    private final AudioClip myLoseSFX = makeSound(getClass().getResource("lose.mp3"));
    
    /**Line clear sounds.*/
    private final AudioClip myLineSFX = makeSound(getClass().getResource("clear.wav"));
    
    /**Button sounds.*/
    private final AudioClip myButtonSFX = makeSound(getClass().getResource("button.wav"));
    
    /**Toggle button sounds.*/
    private final AudioClip myButtonTSFX = makeSound(getClass().getResource("tbutton.wav"));
    
    /**Place block sounds.*/
    private final AudioClip myPlaceSFX = makeSound(getClass().getResource("place.wav"));

    /**
     * Constructor.
     */
    public GUI() {
        setup();
    }
    

    /**
     * Start.
     */
    private void setup() {
        //Frame setup
        myTimer = new Timer(mySpeed, new TimerAction());
        myBoard.addPropertyChangeListener(this);
        myFrame.addKeyListener(new MoveKeys());
        myFrame.setSize(myLarge);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Tetris panel
        myFrame.add(myBPanel, BorderLayout.CENTER);
        myBPanel.setBackground(Color.WHITE);
        myBPanel.setBorder(makeBorder());
        //Pause label
        myPText.setFont(myPText.getFont().deriveFont(LRG_PAUSE));
        myBPanel.add(myPText, BorderLayout.NORTH);
        //Menu bar
        myFrame.setJMenuBar(myBar);
        myBar.add(myGame);
        myGame.add(myStart);
        myGame.add(myEnd);
        myBar.add(mySizeS);
        //Game state options
        myStart.addActionListener(new NewGame());
        myEnd.addActionListener(new EndGame());
        myEnd.setEnabled(false);
        //Sizing options and buttons
        mySizeS.add(mySmlB);
        mySizeS.add(myMedB);
        mySizeS.add(myLargB);
        final ButtonGroup group = new ButtonGroup();
        group.add(mySmlB);
        group.add(myMedB);
        group.add(myLargB);
        mySmlB.addActionListener(new SizeSm());
        myMedB.addActionListener(new SizeMed());
        myLargB.addActionListener(new SizeLg());
        myLargB.setSelected(true);
        frameSetUp();
        //Show frame
        myFrame.setVisible(true);
    }
    
    /**
     * Sets up frame contents.
     */
    private void frameSetUp() {
        //Right panel things
        myFrame.add(myPanel, BorderLayout.EAST);
        myPanel.add(myNPiece);
        myNPiece.setBorder(makeBorder());
        myPanel.add(mySScore);
        mySScore.setBorder(makeBorder());
        myPanel.add(myGameS);
        myGameS.setBorder(makeBorder());
        myPanel.add(myControl);
        myControl.setBorder(makeBorder());
        //Game info panel
        mySScore.add(myScoreT);
        myScoreT.setText("Score: " + myScore);
        mySScore.add(myLevelT);
        myLevelT.setText("Level: 1");
        mySScore.add(myLText);
        myLText.setText("Lines Cleared: " + myLines);
        mySScore.add(myNLine);
        myNLine.setText("Until Next Level: " + (SCORE_CHANGE - myLines % SCORE_CHANGE));
        mySScore.setBackground(Color.GRAY);
        //Game options panel
        myGameS.add(myNewB);
        myGameS.add(myGrid);
        myGameS.add(myAbout);
        myGameS.add(myMute);
        myGrid.addActionListener(new GridListener());
        myAbout.addActionListener(new AboutListener());
        myMute.addActionListener(new MuteListener());
        myNewB.addActionListener(new NewGame());
        myEndB.addActionListener(new EndGame());
        //Controls panel
        myControl.add(new JLabel("Controls:"));
        myControl.add(new JLabel("Rotate: Up Arrow/W"));
        myControl.add(new JLabel("Left: Left Arrow/A"));
        myControl.add(new JLabel("Right: Right Arrow/D"));
        myControl.add(new JLabel("Down: Down Arrow/S"));
        myControl.add(new JLabel("Drop: Space"));
        myControl.add(new JLabel("Pause: P"));
        myControl.setBackground(Color.LIGHT_GRAY);
    }
    
//    private void musicSetup() {
//      //Music setup (Trial)
//        new javafx.embed.swing.JFXPanel();
//        final String uriString = new File("song.mp3").toURI().toString();
//        final Media media = new Media(uriString);
//        final MediaPlayer player = new MediaPlayer(media);
//        player.play();    
//    }
    
    /**
     * Makes sound.
     * @param theUrl URL of file
     * @return Audio clip object
     */
    private AudioClip makeSound(final URL theUrl) {
        final String mediaStringUrl = theUrl.toExternalForm();
        return new AudioClip(mediaStringUrl);
//        return audioClip;
    }
    
    /**
     * Makes a button.
     * @param theName name of button
     * @return a button
     * */
    private JButton makeButton(final String theName) {
        final JButton button = new JButton(theName);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(makeBorder());
        return button;
    }
    
    /**
     * Makes a toggle button.
     * @param theName name of toggle button
     * @return a toggle button
     */
    private JToggleButton makeTButton(final String theName) {
        final JToggleButton button = new JToggleButton(theName);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setBorder(makeBorder());
        return button;
    }
    
    /**
     * Makes radio button.
     * @param theString name
     * @return a button
     */
    private JRadioButtonMenuItem makeMButton(final String theString) {
        return new JRadioButtonMenuItem(theString);
//        return button;
    }
    
    /**
     * Makes border object.
     * @return black border
     */
    private Border makeBorder() {
        final Border line = new LineBorder(Color.BLACK);
        final Border margin = new EmptyBorder(5, 15, 5, 15);
        return new CompoundBorder(line, margin);
//        return compound;
    }
    
    
    /**
     * Property change listener.
     * @param theEvent the event
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        //Move piece
        if (theEvent.getPropertyName().equals("piece moved")) {
            final String buffer = (String) theEvent.getNewValue();
            myBPanel.setMyBoardS(buffer);
            myBPanel.repaint();
        }
        //Game end        
        if (theEvent.getPropertyName().equals("game over")) {
            if (myPause) {
                myPause = !myPause;
                myPText.setText("");
                myInGame = true;
            }
            myTimer.stop();
            myStart.setEnabled((boolean) theEvent.getNewValue());
            myEndB.setVisible(false);
            myNewB.setVisible(true);
            myGameS.remove(myEndB);
            myGameS.add(myNewB, 0);
            if (myInGame) {
                if (mySound) {
                    myLoseSFX.play();
                }
                JOptionPane.showMessageDialog(myFrame, "Game Over!", 
                                              myFrame.getTitle(), 
                                              JOptionPane.INFORMATION_MESSAGE);
                myInGame = false;
                myEnd.setEnabled(false);
            }
        }
        //Line clear
        if (theEvent.getPropertyName().equals("lines cleared")) {
            final Integer[] temp = (Integer[]) theEvent.getNewValue();
            if (mySound) {
                myLineSFX.play();
            }
            
            for (int i = 0; i < temp.length; i++) {
                myScore += SCORE_CHANGE;
                myLines += 1;
                myScoreT.setText("Score: " + myScore);
                if (myLines % SCORE_CHANGE == 0) {
                    myLevel += 1;
                    mySpeed -= SPEED_CHANGE;
//                    mySpeed = Math.floorDiv(mySpeed, myLevel);
                    myLevelT.setText("Level: " + myLevel);
                    if (mySpeed >= SPEED_CHANGE) {
                        myTimer.setDelay(mySpeed);
                    } else {
                        myTimer.setDelay(SPEED_CHANGE);
                    }
                }
            }
            
            myLText.setText("Lines Cleared: " + myLines);
            myNLine.setText("Until Next Level: " + (SCORE_CHANGE - myLines % SCORE_CHANGE));
        }
        //Preview
        if (theEvent.getPropertyName().equals("preview")) {
            //Next piece
            final String buffer = (String) theEvent.getNewValue();
            myNPiece.setMyNext(buffer);
            myNPiece.repaint();
        }
        //Block placed
        if (theEvent.getPropertyName().equals("piece placed") && mySound && myInGame) {
            myPlaceSFX.play();
        }
    }

    /**
     * Timer action class. 
     */
    class TimerAction implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.down();
        }
    }
    
    /**
     * Key listener class.
     */
    class MoveKeys implements KeyListener {
        @Override
        public void keyReleased(final KeyEvent theEvent) {
            final int key = theEvent.getKeyCode();
            //Pause
            if (key == KeyEvent.VK_P) {
                if (!myPause && myInGame) {
                    myTimer.stop();
                    myPause = !myPause;
                    myPText.setText("Paused");
                    myInGame = false;
                    myButtonSFX.play();
                } else {
                    myTimer.start();
                    myPause = !myPause;
                    myPText.setText("");
                    myInGame = true;
                    myButtonSFX.play();
                }
            }
//            if (theEvent.getKeyCode() == KeyEvent.VK_SPACE) {
//                myBoard.drop();
//            }
        }
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            final int key = theEvent.getKeyCode();
            if (myInGame) {
                //Game controls
                switch (key) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        myBoard.left();
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        myBoard.right();
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        myBoard.down();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        myBoard.rotate();
                        break;
                    case KeyEvent.VK_SPACE:
                        myBoard.drop();
                        break;
                    default:
                        break;
                }
            }
        }
        
        @Override
        public void keyTyped(final KeyEvent theEvent) {
        }
    }
    
    /**New game action.*/
    class NewGame implements ActionListener {
        //New game
        @Override
        public void actionPerformed(final ActionEvent theE) {
            myBoard.newGame();
            myTimer.start();
            myInGame = true;
            myStart.setEnabled(false);
            myPause = false;
            myEnd.setEnabled(true);
            myScore = 0;
            myLevel = 1;
            myLines = 0;
            mySpeed = OG_SPEED;
            myTimer.setDelay(mySpeed);
            myLevelT.setText("Level: " + myLevel);
            myLText.setText("Lines Cleared: " + myLines);
            myNLine.setText("Until Next Level: " + (SCORE_CHANGE - myLines % SCORE_CHANGE));
            myEndB.setVisible(true);
            myNewB.setVisible(false);
            myGameS.add(myEndB, 0);
            myGameS.remove(myNewB);
            myFrame.requestFocus();
            if (mySound) {
                myButtonSFX.play();
//                musicSetup();
            }
        }
    }
    
    /**End game action.*/
    class EndGame implements ActionListener {
        //Game end
        @Override
        public void actionPerformed(final ActionEvent theE) {
            if (myPause) {
                myTimer.start();
                myPause = !myPause;
                myPText.setText("");
                myInGame = true;
            }
            myTimer.stop();
            myStart.setEnabled(true);
            myEnd.setEnabled(false);
            myEndB.setVisible(false);
            myNewB.setVisible(true);
            myGameS.remove(myEndB);
            myGameS.add(myNewB, 0);
            if (myInGame) {
                if (mySound) {
                    myLoseSFX.play();
                }
                JOptionPane.showMessageDialog(myFrame, "Game Over", 
                                              myFrame.getTitle(), JOptionPane.PLAIN_MESSAGE);
                myInGame = false;
            }
        }
    }
    
    /**Grid button action.*/
    class GridListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theE) {
            if (mySound) {
                myButtonTSFX.play();
            }
            myBPanel.myGrid();
            myFrame.requestFocus();
        }
    }
    
    /**About listener.*/
    class AboutListener implements ActionListener {
        //About pane
        @Override
        public void actionPerformed(final ActionEvent theE) {
            if (mySound) {
                myButtonSFX.play();
            }
            if (!myPause && myInGame) {
                myTimer.stop();
                myPause = !myPause;
                myPText.setText("Paused");
                myInGame = false;
            }
            JOptionPane.showMessageDialog(myFrame, "Earn 5 points for every line cleared."
                            + "\nIncrease speed every 5 levels.",
                            myFrame.getTitle(), JOptionPane.PLAIN_MESSAGE);
            myFrame.requestFocus();
        }
    }

    /**Mute listener.*/
    class MuteListener implements ActionListener {
        //Mute
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            mySound = !mySound;
            if (mySound) {
                myButtonTSFX.play();
            }
            myFrame.requestFocus();
        }
    }

    /**
     * The Class SizeSm.
     */
    class SizeSm implements ActionListener {
        //Small size
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            myFrame.setSize(mySmall);
            myFrame.setLocationRelativeTo(null);
            myGrid.setFont(myGrid.getFont().deriveFont(SML_FONT));
            myNewB.setFont(myNewB.getFont().deriveFont(SML_FONT));
            myEndB.setFont(myEndB.getFont().deriveFont(SML_FONT));
            myAbout.setFont(myAbout.getFont().deriveFont(SML_FONT));
            myMute.setFont(myMute.getFont().deriveFont(SML_FONT));
            myPText.setFont(myPText.getFont().deriveFont(SML_PAUSE));
            if (mySound) {
                myButtonTSFX.play();
            }
        }
    }
    
    /**
     * The Class SizeMed.
     */
    class SizeMed implements ActionListener {
        //Medium size
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            myFrame.setSize(myMed);
            myFrame.setLocationRelativeTo(null);
            myGrid.setFont(myGrid.getFont().deriveFont(MED_FONT));
            myNewB.setFont(myNewB.getFont().deriveFont(MED_FONT));
            myEndB.setFont(myEndB.getFont().deriveFont(MED_FONT));
            myAbout.setFont(myAbout.getFont().deriveFont(MED_FONT));
            myMute.setFont(myMute.getFont().deriveFont(MED_FONT));
            myPText.setFont(myPText.getFont().deriveFont(MED_PAUSE));
            if (mySound) {
                myButtonTSFX.play();
            }
        }
    }
    
    /**
     * The Class SizeLg.
     */
    class SizeLg implements ActionListener {
        //Large size
        @Override
        public void actionPerformed(final ActionEvent arg0) {
            myFrame.setSize(myLarge);
            myFrame.setLocationRelativeTo(null);
            myGrid.setFont(myGrid.getFont().deriveFont(LRG_FONT));
            myNewB.setFont(myNewB.getFont().deriveFont(LRG_FONT));
            myEndB.setFont(myEndB.getFont().deriveFont(LRG_FONT));
            myAbout.setFont(myAbout.getFont().deriveFont(LRG_FONT));
            myMute.setFont(myMute.getFont().deriveFont(LRG_FONT));
            myPText.setFont(myPText.getFont().deriveFont(LRG_PAUSE));
            if (mySound) {
                myButtonTSFX.play();
            }
        }
    }
}
