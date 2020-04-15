package model;

import java.util.Map;

/**
 * The Class Car.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class Car extends AbstractVehicle implements Vehicle {

    /** Dead for this many turns.*/
    private static final int DEATH_TIME = 5;
    
    /**
     * Instantiates a new car.
     *
     * @param theX the X
     * @param theY the Y
     * @param theDir the direction
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
    }
    
    /**
     * Can pass through streets, 
     * traffic lights when green or yellow, and cross walks when green.
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
            if (theTerrain.equals(Terrain.CROSSWALK)) {
                if (theLight.equals(Light.YELLOW) || theLight.equals(Light.RED)) {
                    return false; //Can't cross yellow or red cross walks
                }
            }
            if (theTerrain.equals(Terrain.LIGHT) && theLight.equals(Light.RED)) {
                return false; //Can't cross red traffic lights
            }
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
//        //Stays on traffic lights or cross walks
//        if (theNeighbors.get(myDir) == Terrain.LIGHT 
//                        || theNeighbors.get(myDir) == Terrain.CROSSWALK) {
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
//        //Checks around for lights or cross walks before reversing
//        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
//            if (!entry.getKey().equals(myDir.reverse())) {
//                if (entry.getValue() == Terrain.LIGHT 
//                                || entry.getValue() == Terrain.CROSSWALK) {
//                    return entry.getKey();
//                }
//            }
//        }
//        //Last Resort
//        return myDir.reverse();
    }
    
    /**
     * Stays on traffic lights and cross walks. 
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper1(final Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(myDir) == Terrain.LIGHT 
                        || theNeighbors.get(myDir) == Terrain.CROSSWALK) {
            return myDir;
        }
        return helper2(theNeighbors);
    }
    
    /**
     * Straight then left then right. 
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper2(final Map<Direction, Terrain> theNeighbors) {       
        if (theNeighbors.get(myDir) == Terrain.STREET) {
            return myDir;
        } else if (theNeighbors.get(myDir.left()) == Terrain.STREET
                        || helper3(theNeighbors) == myDir.left()) {
            return myDir.left();
        } else if (theNeighbors.get(myDir.right()) == Terrain.STREET) {
            return myDir.right();
        }
        return helper3(theNeighbors);
    }
    
    /**
     * Checks for traffic lights and cross walks. 
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper3(final Map<Direction, Terrain> theNeighbors) {       
        for (Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
            if (!entry.getKey().equals(myDir.reverse())) {
                if (entry.getValue() == Terrain.LIGHT 
                                || entry.getValue() == Terrain.CROSSWALK) {
                    return entry.getKey();
                }
            }
        }
        return helper4(theNeighbors);
    }
    
    /**
     * Last Resort.
     *
     * @param theNeighbors the neighbors
     * @return the direction
     */
    public Direction helper4(final Map<Direction, Terrain> theNeighbors) { 
        return myDir.reverse();
    }
}
