package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Shows next piece.
 * @author Gordon Tran
 * @version November 
 */
public class NextPanel extends JPanel {
    /**Constant integer.*/
    private static final int FOUR = 4;
    /**Constant integer.*/
    private static final int FIVE = 5;
    /**Constant integer.*/
    private static final int SIX = 6;

    /**
     * Generated ID. 
     */
    private static final long serialVersionUID = 6108123800894468548L;
    /**Next piece.*/
    private String[] myNext = new String[0];
    
    /**Constructor to make the label.*/
    public NextPanel() {
        super();
        this.add(new JLabel("Next Piece"));
    }
    
    /**Sets new piece.
     * @param theNext String of new piece 
     */
    public void setMyNext(final String theNext) {
        final String[] output = theNext.split("\n");
        myNext = output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theG) {
        final Graphics2D g2 = (Graphics2D) theG;
        super.paintComponent(g2);
        
        for (int j = 0; j < myNext.length; j++) {
            for (int i = 0; i < myNext[j].length(); i++) {
                if (myNext[j].charAt(i) != ' ') {
                    g2.setColor(letterColor(myNext[j].charAt(i)));
                    g2.fill(new Rectangle2D.Double((i * this.getHeight() / FIVE) 
                                                   + this.getWidth() / SIX, 
                                                   (j * this.getHeight() / FIVE) 
                                                   + this.getHeight() / FOUR, 
                                                   this.getHeight() / FIVE, 
                                                   this.getHeight() / FIVE));
                    g2.setColor(Color.BLACK);
                    g2.draw(new Rectangle2D.Double((i * this.getHeight() / FIVE) 
                                                   + this.getWidth() / SIX,
                                                   (j * this.getHeight() / FIVE) 
                                                   + this.getHeight() / FOUR,
                                                   this.getHeight() / FIVE, 
                                                   this.getHeight() / FIVE));
                    
                }
            }
        }
//        g2.draw(new Line2D.Double(0, 10, 100, 100));
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
