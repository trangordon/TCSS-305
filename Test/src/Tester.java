import java.util.Map;

import model.Direction;
import model.Terrain;

public class Tester {

	public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
		return helper1(theNeighbors);
	}
    
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
        
	public Direction helper2(final Map<Direction, Terrain> theNeighbors) {
        if (theNeighbors.get(myDir).equals(Terrain.CROSSWALK) 
                        || theNeighbors.get(getDirection()).equals(Terrain.LIGHT)) {
            return myDir;
        }
        return helper3(theNeighbors);
	}
        
	public Direction helper3(final Map<Direction, Terrain> theNeighbors) { 
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
	
	public Direction helper5(final Map<Direction, Terrain> theNeighbors) {
        return myDir.reverse();
    }

}
