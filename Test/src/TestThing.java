import java.util.Map;

import model.Direction;
import model.Terrain;

public class TestThing{
	public Direction helper1(final Map<Direction, Terrain> theNeighbors) {
	    //Stays on lights
	    if (theNeighbors.get(myDir) == Terrain.LIGHT) {
	        return myDir;
	    }
	    return helper2(theNeighbors);
	}
	public Direction helper2(final Map<Direction, Terrain> theNeighbors) {
	    //Stays on cross walks for a bit
	    if (theNeighbors.get(myDir) == Terrain.CROSSWALK) {
	        myCrossTimer -= 1;
	        return myDir;
	    }
	    return helper3(theNeighbors);
	}
	public Direction helper3(final Map<Direction, Terrain> theNeighbors) {    
	    //Straight then Left then Right
	    if (theNeighbors.get(myDir) == Terrain.STREET) {
	        return myDir;
	    } else if (theNeighbors.get(myDir.left()) == Terrain.STREET) {
	        return myDir.left();
	    } else if (theNeighbors.get(myDir.right()) == Terrain.STREET) {
	        return myDir.right();
	    }
	    return helper4(theNeighbors);
	}
	
	public Direction helper4(final Map<Direction, Terrain> theNeighbors) {
	    //Checks for lights and cross walks before reversing
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
	
	public Direction helper5(final Map<Direction, Terrain> theNeighbors) {    
	    //Last Resort
	    return myDir.reverse();
	}
	
		
	
}