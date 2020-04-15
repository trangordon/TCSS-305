package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class Human.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class Human extends AbstractVehicle implements Vehicle {
    
    /** Dead for this many turns.*/
    private static final int DEATH_TIME = 25;
    
    /**
     * Instantiates a new human.
     *
     * @param theX the X
     * @param theY the Y
     * @param theDir the direction
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Passes through grass and red or yellow cross walks.
     *
     * @param theTerrain Terrain type
     * @param theLight Light status
     * @return true, if successful
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain.equals(Terrain.GRASS)) {
            return true;
        }
        if (theTerrain.equals(Terrain.CROSSWALK)) {
            if (theLight.equals(Light.RED) || theLight.equals(Light.YELLOW)) {
                return true; //Only passes through red and yellow cross walks
            }
        }
        return false;
    }

    /**
     * Walks around randomly but stops and goes for cross walks.
     *
     * @param theNeighbors Map of adjacent tiles
     * @return the direction
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        //Checks for cross walks
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() == Terrain.CROSSWALK) {
                    return entry.getKey();
                }
            }
        }
        
        //Reverse Section
        if (chooseDirHelper(theNeighbors)) {
            return myDir.reverse();
        }
        
        //Random
        final List<Direction> dChoices = new ArrayList<Direction>(0);
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (entry.getKey() != myDir.reverse()) {
                if (entry.getValue() == Terrain.GRASS
                                || entry.getValue() == Terrain.CROSSWALK) {
                    dChoices.add(entry.getKey());
                    
                }
            }
        }
        return dChoices.get(myRand.nextInt(dChoices.size()));
    }
    
    /**
     * Choose Direction Helper method.
     * 
     * @param theNeighbors Map of adjacent tiles 
     * @return true if should reverse
     */
    public boolean chooseDirHelper(final Map<Direction, Terrain> theNeighbors) {
        int temp = 0;
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() != Terrain.GRASS
                                && entry.getValue() != Terrain.CROSSWALK) {
                    temp += 1;
                }
            }
        }
        if (temp == theNeighbors.size() - 1) {
            return true;
        }
        return false;
    }
}
