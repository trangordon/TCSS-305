/* Gordon Tran
 * 
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ShoppingCart.
 * @author Gordon Tran
 * @version October 2018
 */
public class ShoppingCart {

    
    /** The Item list. */
    private List<ItemOrder> myList = new ArrayList<ItemOrder>();
    
    /** The total. */
    private BigDecimal myTotal = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN);
    
    /** The my size. */
    private int mySize;
    
    
    /** The discount. */
    private final BigDecimal myDiscount = BigDecimal.valueOf(0.85);
    
    /**
     * Instantiates a new shopping cart.
     */
    public ShoppingCart() {
        myList = new ArrayList<ItemOrder>();
        mySize = 0;
    }
    
    /**
     * Adds to the cart.
     *
     * @param theOrder the order
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < mySize; i++) {
            if (myList.get(i).equals(theOrder)) {
                //
                System.out.println("test");
            }
        }
        mySize += 1;
        myList.add(theOrder);
        
        
    }


    /**
     * Sets the membership.
     *
     * @param theMembership the new membership
     */
    public void setMembership(final boolean theMembership) {
        if (theMembership) {
            myTotal = myTotal.multiply(myDiscount);
        }
    }


    /**
     * Calculate total.
     *
     * @return the big decimal total
     */
    public BigDecimal calculateTotal() {
        myTotal = BigDecimal.valueOf(0);
        for (int i = 0; i < mySize; i++) {
            final ItemOrder iOTest = myList.get(i);
            final Item iTest = iOTest.getItem();
            if (iTest.isBulk()) {
                
                myTotal = myTotal.add(iTest.getBulkPrice());
            } else {
                myTotal = myTotal.add(iTest.getPrice());
            }
        }
        return myTotal;
    }
    
    /**
     * Clears the list.
     */
    public void clear() {
        mySize = 0;
        myList = new ArrayList<ItemOrder>(); 
        myTotal = BigDecimal.valueOf(0);
    }

    
    @Override
    public String toString() {
        String buffer = "";
        for (int i = 0; i < mySize; i++) {
            final ItemOrder iOTest = myList.get(i);
            buffer = buffer + iOTest.toString();
        }
        
        return buffer;
    }

}
