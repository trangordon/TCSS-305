/**Gordon Tran
 * Assignment 3
 * TCSS 305
 */
package model;

import java.util.Map;
import java.util.Random;

/**
 * Base for all vehicles in the model. 
 * 
 * @author Gordon Tran
 * @version October 2018
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /** The X value. */
    protected int myX;
    
    /** The Y value. */
    protected int myY;
    
    /** The direction. */
    protected Direction myDir;
    
    /** The starting X value. */
    protected final int mySX;
    
    /** The starting Y value. */
    protected final int mySY;
    
    /** The starting direction. */
    protected final Direction mySDir;
    
    /** Boolean to say if alive or not.*/
    protected boolean myLife = true; 

    /** Death count integer.*/
    protected int myDeathTime;
    
    /** Death count integer.*/
    protected final int mySDeathTime;
    
    /** Random thing.*/
    protected final Random myRand = new Random();

    /**
     * The constructor for vehicles. 
     * 
     * @param theX starting X coordinate
     * @param theY starting Y coordinate
     * @param theDir Starting direction
     * @param theDeathTime Vehicle death time
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                              final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDir = theDir;
        mySX = theX;
        mySY = theY;
        mySDir = theDir;
        myDeathTime = theDeathTime;
        mySDeathTime = theDeathTime;
    }

    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    @Override
    public final void collide(final Vehicle theOther) {
        if (myLife) {
            if (getDeathTime() > ((Vehicle) theOther).getDeathTime()) {
                myLife = false;
                myDeathTime *= 2;
            }
        }
    }

    @Override
    public final int getDeathTime() {
        return myDeathTime;
    }

    @Override
    public String getImageFileName() {
        if (myLife) {
            return getClass().getSimpleName().toLowerCase() + ".gif";
        }
        return getClass().getSimpleName().toLowerCase() + "_dead.gif";
    }

   
    @Override
    public final Direction getDirection() {
        return myDir;
    }

    @Override
    public final int getX() {
        return myX;
    }

    @Override
    public final int getY() {
        return myY;
    }

    @Override
    public boolean isAlive() {
        if (this.myDeathTime == mySDeathTime) {
            myLife = true;
        }
        return myLife;
    }

    @Override
    public final void poke() {
        myDeathTime -= 1;
        myDir = Direction.random();

    }

    @Override
    public void reset() {
        myX = mySX;
        myY = mySY;
        myDir = mySDir;
        myLife = true;
        myDeathTime = mySDeathTime;
    }

    @Override
    public final void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    @Override
    public final void setX(final int theX) {
        myX = theX;
    }

    @Override
    public final void setY(final int theY) {
        myY = theY;
    }

    /**
     * Returns string of name of vehicle and death timer if dead.
     * 
     * @return "Name" |Timer: "Turns"
     */
    @Override
    public String toString() {
        String buffer = getClass().getSimpleName();
        if (!myLife) {
            buffer = buffer + "|Timer: " + (this.getDeathTime() - mySDeathTime);
        }
        return buffer;
    }
    
    /** 
     * Random method.
     * 
     *  @param theBound Bounding integer
     *  @return Randomized integer
     */
    public final int randomize(final int theBound) {
        return myRand.nextInt(theBound);
        
    }
}
