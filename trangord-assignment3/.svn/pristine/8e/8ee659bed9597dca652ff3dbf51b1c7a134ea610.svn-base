package model;

import java.util.Map;

/**
 * The Class Bicycle.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class Bicycle extends AbstractVehicle implements Vehicle {

    /** Dead for this many turns.*/
    private static final int DEATH_TIME = 20;
    
    /**
     * Instantiates a new bicycle.
     *
     * @param theX the X
     * @param theY the Y
     * @param theDir the direction
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Can go through trails, streets, and green traffic lights and cross walks.
     *
     * @param theTerrain Terrain type
     * @param theLight Light status
     * @return true, if successful
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (theTerrain.equals(Terrain.STREET) 
                        || theTerrain.equals(Terrain.LIGHT) 
                        || theTerrain.equals(Terrain.CROSSWALK)
                        || theTerrain.equals(Terrain.TRAIL)) {
            if (theTerrain.equals(Terrain.LIGHT) 
                            || theTerrain.equals(Terrain.CROSSWALK)) {
                if (theLight.equals(Light.RED) || theLight.equals(Light.YELLOW)) {
                    return false; 
                    /*Can't pass through red and yellow 
                    traffic lights and cross walks
                    */
                }    
            }
            return true;
        }
        return false;
    }

    /**
     * Goes for trails first. Goes straight then left then right on streets. 
     * Stops for for non-green lights.
     *
     * @param theNeighbors Map of adjacent tiles
     * @return the direction
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return helper1(theNeighbors);
//      //Always goes on trails
//        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
//            if (!entry.getKey().equals(myDir.reverse())) {
//                if (entry.getValue() == Terrain.TRAIL) {
//                    return entry.getKey();
//                }
//            }
//        }
//        
//        //Stays on lights and cross walks
//        if (theNeighbors.get(myDir).equals(Terrain.CROSSWALK) 
//                        || theNeighbors.get(getDirection()).equals(Terrain.LIGHT)) {
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
//                if (entry.getValue() == Terrain.CROSSWALK 
//                                || entry.getValue() == Terrain.LIGHT) {
//                    return entry.getKey();
//                }
//            }
//        }
//        
//        //Last Resort
//        return myDir.reverse();
    }
    
    /**
     * Going on trails.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper1(final Map<Direction, Terrain> theNeighbors) {
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() == Terrain.TRAIL) {
                    return entry.getKey();
                }
            }
        }
        return helper2(theNeighbors);
    }
        
    /**
     * Stays on lights and cross walks.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper2(final Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(myDir).equals(Terrain.CROSSWALK) 
                        || theNeighbors.get(getDirection()).equals(Terrain.LIGHT)) {
            return myDir;
        }
        return helper3(theNeighbors);
    }
        
    /**
     * Straight then left then right.
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
     * Checks for lights and cross walks.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper4(final Map<Direction, Terrain> theNeighbors) {
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() == Terrain.CROSSWALK 
                                || entry.getValue() == Terrain.LIGHT) {
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
