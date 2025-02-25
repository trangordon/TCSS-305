package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

/**
 * The Class TruckTest.
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public class TruckTest {

    /** The Constant TRIES_FOR_RANDOMNESS. */
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /**
     * Test can pass method.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    assertTrue(truck.canPass(destinationTerrain, currentLightCondition));
                    
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    if (currentLightCondition == Light.GREEN 
                                    || currentLightCondition == Light.YELLOW) {
                        assertTrue(truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else {
                        assertFalse(truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                    
                } else if (destinationTerrain == Terrain.LIGHT) {
                    assertTrue(truck.canPass(destinationTerrain,
                                             currentLightCondition));
                    
                } else if (!validTerrain.contains(destinationTerrain)) {
                    assertFalse(truck.canPass(destinationTerrain, currentLightCondition));
                }
            } 
        }

    }
    
    /**
     * Test choose direction all streets.
     */
    @Test
    public void testChooseDirectionAllStreets() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) {
                seenSouth = true;
            }
        }
 
        assertTrue(seenWest && seenNorth && seenEast);
            
        assertFalse(seenSouth);
    }
    
    /**
     * Test choose direction if reverse is required.
     */
    @Test
    public void testChooseDirectionMustReverse() {
        
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK 
                            && t != Terrain.LIGHT) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                
                assertEquals(Direction.SOUTH, truck.chooseDirection(neighbors));
            }
        }
    }
 
    /**
     * Test choose direction stop at cross walk.
     */
    @Test
    public void testChooseDirectionStopAtCrosswalk() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.GRASS);
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.EAST, Terrain.TRAIL);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) {
                seenSouth = true;
            }
        }
 
        assertFalse(seenWest || seenSouth || seenEast);
            
        assertTrue(seenNorth);

    }


    /**
     * Test truck constructor.
     */
    @Test
    public void testTruck() {
        final Truck t = new Truck(1, 1, Direction.NORTH);
        
        assertEquals(1, t.getX());
        assertEquals(1, t.getY());
        assertEquals(Direction.NORTH, t.getDirection());
        assertEquals(0, t.getDeathTime());
        assertTrue(t.isAlive());
    }
    
    /**
     * Test truck setters.
     */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(1, 1, Direction.NORTH);
        
        t.setX(2);
        assertEquals(2, t.getX());
        t.setY(3);
        assertEquals(3, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, t.getDirection());
    }
}
