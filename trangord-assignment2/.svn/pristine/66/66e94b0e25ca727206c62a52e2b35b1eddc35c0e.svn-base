/* Gordon Tran
 * TCSS 305
 * Assignment 2
 */

package model;

import java.math.BigDecimal;

/**
 * The Class Item.
 * @author Gordon Tran
 * @version October 2018
 */
public final class Item {
    
    /** The name. */
    private String myName;
    
    /** The price. */
    private BigDecimal myPrice;
 
    /** The bulk quantity. */
    private int myBulkQ;
    
    /** The bulk price. */
    private BigDecimal myBulkP;
    
    /**
     * Instantiates a new item.
     *
     * @param theName the the name
     * @param thePrice the the price
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myName = theName;
        myPrice = thePrice;
        myBulkQ = 0;
        myBulkP = null;
    }


    /**
     * Instantiates a new item with more parameters.
     *
     * @param theName the the name
     * @param thePrice the the price
     * @param theBulkQuantity the the bulk quantity
     * @param theBulkPrice the the bulk price
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        myName = theName;
        myPrice = thePrice;
        myBulkQ = theBulkQuantity;
        myBulkP = theBulkPrice;
    }


    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        
        return myName;
    }
    
    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        
        return myPrice;
    }
    

    /**
     * Gets the bulk quantity.
     *
     * @return the bulk quantity
     */
    public int getBulkQuantity() {
        
        return myBulkQ;
    }
    

    /**
     * Gets the bulk price.
     *
     * @return the bulk price
     */
    public BigDecimal getBulkPrice() {
        
        return myBulkP;
    }

    
    /**
     * Checks if is bulk purchase.
     *
     * @return true, if is bulk purchase
     */
    public boolean isBulk() {
        if (myBulkQ == 0) {
            return false;
        }
        return true;
        
    }

    @Override
    public String toString() {
        String buffer = myName + ": $" + myPrice;
        if (myBulkQ != 0) {
            buffer = buffer + " (" + myBulkQ + " for $" + myBulkP + ")"; 
        }
        return buffer;
    }

    @Override
    public boolean equals(final Object theOther) {
        if (theOther == this) {
            return true;
        }
        if (!(theOther instanceof Item)) {
            return false;
        }
        final Item testItem = (Item) theOther;
        if (!this.myName.equals(testItem.getName())) {
            return false;
        }
        if (!this.myPrice.equals(testItem.getPrice())) {
            return false;
        }
        if (this.myBulkQ != (testItem.myBulkQ)) {
            return false;
        }
        if (!this.myBulkP.equals(testItem.getBulkPrice())) {
            return false;
        }
        return true;
    }


    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 4 + myName.hashCode();
        hash = hash * 3 + myPrice.hashCode();
        hash = hash * 11 + myBulkQ;
        hash = hash * 11 + myBulkP.hashCode();
        return hash;
    }

}
