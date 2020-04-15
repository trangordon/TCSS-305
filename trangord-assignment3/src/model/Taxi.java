package model;

import java.util.Map;

/**
 * The Class Taxi.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class Taxi extends AbstractVehicle implements Vehicle {

    /** Dead for this many turns.*/
    private static final int DEATH_TIME = 10;
    
    /** Number of clock cycles taxi has to wait +1.*/
    private static final int CLOCK_WAIT = 4; //4 so taxi waits 3 cycles
    
    /** Instanced field to count timer of cross walk ignoring.*/
    private int myCrossTimer;
    
    /**
     * Instantiates a new taxi.
     *
     * @param theX the X
     * @param theY the Y
     * @param theDir the direction
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Can pass through streets, 
     * traffic lights when green or yellow, and cross walks but waits a bit when red.
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
                if (myCrossTimer == 0) {
                    myCrossTimer = CLOCK_WAIT;
                    return true; //Bypasses red light cross walks
                }
                return false; //Can't pass cross walks on red. For a bit 
            }
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) {
                return false; //Can't pass traffic lights when red
            }
            myCrossTimer = CLOCK_WAIT; //Reset timer when not on cross walk
            return true;
        }
        return false;
    }

    /**
     * Goes straight then left then right on streets, 
     * stops for lights sometimes and reverses as a last resort.
     *
     * @param theNeighbors Map of adjacent tiles
     * @return the direction
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return helper1(theNeighbors);
//        //Stays on lights
//        if (theNeighbors.get(myDir) == Terrain.LIGHT) {
//            return myDir;
//        }
//        
//        //Stays on cross walks for a bit
//        if (theNeighbors.get(myDir) == Terrain.CROSSWALK) {
//            myCrossTimer -= 1;
//            return myDir;
//        }
//        
//        //Straight then Left then Right
//        if (theNeighbors.get(myDir) == Terrain.STREET) {
//            return myDir;
//        } else if (theNeighbors.get(myDir.left()) == Terrain.STREET) {
//            return myDir.left();
//        } else if (theNeighbors.get(myDir.right()) == Terrain.STREET) {
//            return myDir.right();
//        }
//        
//        //Checks for lights and cross walks before reversing
//        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
//            if (!entry.getKey().equals(myDir.reverse())) {
//                if (entry.getValue() == Terrain.LIGHT 
//                                || entry.getValue() == Terrain.CROSSWALK) {
//                    return entry.getKey();
//                }
//            }
//        }
//        
//        //Last Resort
//        return myDir.reverse();
    }
    
    /**
     * Stays on lights.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper1(final Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(myDir) == Terrain.LIGHT) {
            return myDir;
        }
        return helper2(theNeighbors);
    }
    
    /**
     * Stays on cross walks for a bit.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper2(final Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(myDir) == Terrain.CROSSWALK) {
            myCrossTimer -= 1;
            return myDir;
        }
        return helper3(theNeighbors);
    }
    
    /**
     * Straight then Left then Right.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper3(final Map<Direction, Terrain> theNeighbors) {    
        if (theNeighbors.get(myDir) == Terrain.STREET) {
            return myDir;
        } else if (theNeighbors.get(myDir.left()) == Terrain.STREET 
                        || helper4(theNeighbors) == myDir.left()) {
            return myDir.left();
        } else if (theNeighbors.get(myDir.right()) == Terrain.STREET) {
            return myDir.right();
        }
        return helper4(theNeighbors);
    }
    
    /**
     *Checks for lights and cross walks before reversing.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper4(final Map<Direction, Terrain> theNeighbors) {
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() == Terrain.LIGHT 
                                || entry.getValue() == Terrain.CROSSWALK) {
                    return entry.getKey();
                }
            }
        }
        return helper5(theNeighbors);
    }
    
    /**
     * Last Resort.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper5(final Map<Direction, Terrain> theNeighbors) {    
        return myDir.reverse();
    }
}