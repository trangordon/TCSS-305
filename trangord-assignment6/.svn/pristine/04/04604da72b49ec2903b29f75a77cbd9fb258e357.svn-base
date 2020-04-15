package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Tetris Panel.
 * @author Gordon Tran
 * @version November
 */
public class BoardPanel extends JPanel {
    
    /**Constant integer.*/
    private static final int FIVE = 5;
    
    /**Constant integer.*/
    private static final int TWELV = 12;
    
    /**Constant integer.*/
    private static final int TWENT = 20;
    
    /**Constant integer.*/
    private static final int TWENT_SIX = 26;

    /**
     * Generated ID. 
     */
    private static final long serialVersionUID = -3256290564832372809L;
    
    /**The tetris board string.*/
    private String[] myBoardS = new String[0];
    
    /**Grid option.*/
    private boolean myGridOn;
    
    /**
     * Set grid.
     * @param theGrid
     */
    public void myGrid() {
        myGridOn = !myGridOn;
        repaint();
        
    }

    /**Sets board string.
     * @param theBoardS the string
     */
    public void setMyBoardS(final String theBoardS) {
        final String[] output = theBoardS.split("\n");
        myBoardS = output;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theG) {
        final Graphics2D g2 = (Graphics2D) theG;
        super.paintComponent(g2);
        if (myGridOn && myBoardS.length == 0) {
            for (int r = FIVE; r < TWENT_SIX; r++) {
                for (int s = 1; s < TWELV; s++) {
                    g2.draw(new Line2D.Double((s - 1) * this.getHeight() / TWENT, 
                                              (r - FIVE) * this.getHeight() / TWENT,
                                              (s - 1) * this.getHeight() / TWENT,
                                              this.getHeight()));
                    g2.draw(new Line2D.Double(0, 
                                              (r - FIVE) * this.getHeight() / TWENT,
                                              this.getWidth(),
                                              (r - FIVE) * this.getHeight() / TWENT));
                }
            }
        }
        
        
//        int row = 0;
        for (int j = FIVE; j < myBoardS.length; j++) {
            for (int i = 1; i < myBoardS[j].length(); i++) {
                if (myGridOn) {
                    g2.draw(new Line2D.Double((i - 1) * this.getHeight() / TWENT, 
                                              (j - FIVE) * this.getHeight() / TWENT,
                                              (i - 1) * this.getHeight() / TWENT,
                                              this.getHeight()));
                    g2.draw(new Line2D.Double(0, 
                                              (j - FIVE) * this.getHeight() / TWENT,
                                              this.getWidth(),
                                              (j - FIVE) * this.getHeight() / TWENT));
                }
                
                if (myBoardS[j].charAt(i) != ' ') { 
                    //&& myBoardS[j].charAt(i) != '|' && myBoardS[j].charAt(i) != '-' ) {
                    g2.setColor(letterColor(myBoardS[j].charAt(i)));
                    
                    g2.fill(new Rectangle2D.Double((i - 1) * this.getHeight() / TWENT, 
                                                   (j - FIVE) * this.getHeight() / TWENT,
                                                   this.getHeight() / TWENT,
                                                   this.getHeight() / TWENT));
                    g2.setColor(Color.BLACK);
                    g2.draw(new Rectangle2D.Double((i - 1) * this.getHeight() / TWENT, 
                                                   (j - FIVE) * this.getHeight() / TWENT,
                                                   this.getHeight() / TWENT,
                                                   this.getHeight() / TWENT));
                }
            }
        }
//        for (final String line: myBoardS) {
//            for (int i = 0; i < line.length(); i++) {
//                if (line.charAt(i) != ' ')
//                    g2.draw(new Rectangle2D.Double(i, row, 30, 30));
//            }
//            row += 1;
//        }
        
    }
    
    /**
     * Sets color based on given char. 
     * @param theChar the given char
     * @return color 
     */
    private Color letterColor(final char theChar) {
        Color c = null;
        switch (theChar) {
            case 'I': c = Color.CYAN;
                break;
            case 'J': c = Color.ORANGE;
                break;
            case 'L': c = Color.BLUE;
                break;
            case 'O': c = Color.YELLOW;
                break;
            case 'S': c = Color.GREEN;
                break;
            case 'T': c = Color.MAGENTA;
                break;
            case 'Z': c = Color.RED;
                break;
            default: 
                break;
        }
        return c;
    }
}
