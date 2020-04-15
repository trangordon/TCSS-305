package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class Truck.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class Truck extends AbstractVehicle implements Vehicle { 
    
    /** Dead for this many turns.*/
    private static final int DEATH_TIME = 0;
    
    /**
     * Instantiates a new truck.
     *
     * @param theX the X
     * @param theY the Y
     * @param theDir the direction
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }

    /**
     * Passes through streets lights and cross walks but only when not red.
     *
     * @param theTerrain Terrain type
     * @param theLight Light status
     * @return true, if successful
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain.equals(Terrain.STREET) 
                        || theTerrain.equals(Terrain.LIGHT) 
                        || theTerrain.equals(Terrain.CROSSWALK)) {
            if (theTerrain.equals(Terrain.CROSSWALK) && theLight.equals(Light.RED)) {
                return false; //Can't cross red cross walks
            }
            return true; 
        }
        return false;
    }

    /**
     * Goes in any direction except reverse unless it has to.
     * 
     * @param theNeighbors Map of adjacent tiles
     * @return buffer Random direction
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        //Reverse Section
        if (chooseDirHelper(theNeighbors)) {
            return myDir.reverse();
        }

        //Random
        final List<Direction> dChoices = new ArrayList<Direction>(0);
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (entry.getKey() != myDir.reverse()) {
                if (entry.getValue() == Terrain.STREET
                                || entry.getValue() == Terrain.CROSSWALK
                                || entry.getValue() == Terrain.LIGHT) {
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
                if (entry.getValue() != Terrain.STREET
                                && entry.getValue() != Terrain.CROSSWALK
                                && entry.getValue() != Terrain.LIGHT) {
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
